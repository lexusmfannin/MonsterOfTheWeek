package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Comment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class JdbcCommentDao implements CommentDao{
    private List<Comment> comments = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;
    public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Comment> getAllComments(){
        return Collections.unmodifiableList(comments);
    }

    @Override
    public Comment createComment(Comment comment, int userId) {
        try {
            String commentSql = "INSERT INTO messages " +
                                "(user_id, comment_title, comment_box, comment_timestamp) " +
                                "VALUES (?, ?, ?, CURRENT_DATE) RETURNING comment_id;";

            int newCommentId = jdbcTemplate.queryForObject(
                    commentSql,
                    int.class,
                    comment.getCommentTitle(),
                    comment.getCommentBox(),
                    userId
            );
            return getCommentByCommentId(newCommentId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating comment", e);
        }
    }

    @Override
    public Comment updateComment(Comment comment) {
        try {
            String sql = "UPDATE messages SET comment_title = ?, comment_box = ? WHERE comment_id = ?;";

            int rowsUpdated = jdbcTemplate.update(
                    sql,
                    comment.getCommentTitle(),
                    comment.getCommentBox(),
                    comment.getCommentId()
            );
            if (rowsUpdated > 0) {
                return comment;
            } else {
                throw new RuntimeException("Comment not found or not updated");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating comment", e);
        }
    }

    @Override
    public Comment deleteComment(int userId, int commentId) {
        try {
            String sql = "DELETE FROM messages WHERE user_id = ? AND comment_id = ?";

            int rowsDeleted = jdbcTemplate.update(sql, userId, commentId);

            if (rowsDeleted == 0) {
                throw new RuntimeException("Comment not found for deletion");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error deleting comment", e);
        }
        return null;
    }

    public Comment getCommentByCommentId(int commentId) {
        String sql = "SELECT comment_id, user_id, comment_title, comment_box, comment_timestamp " +
                     "FROM messages " +
                     "WHERE comment_id = ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, commentId);

        if (result.next()) {
                return mapRowToComment(result);
            }

        return null;
    }

    private Comment mapRowToComment(SqlRowSet rowSet) {
        Comment comment = new Comment();
        comment.setCommentId(rowSet.getInt("comment_id"));
        comment.setCommentTitle(rowSet.getString("comment_title"));
        comment.setCommentBox(rowSet.getString("comment_box"));
        comment.setTimestamp(rowSet.getDate("comment_timestamp"));
        return comment;
    }

}
