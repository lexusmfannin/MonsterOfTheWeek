package com.techelevator.dao;
import com.techelevator.model.Comment;
import java.util.List;
public interface CommentDao {
    List<Comment> getAllComments();
    Comment createComment(Comment comment, int userId);
    Comment updateComment(Comment comment);
    Comment deleteComment(int userId, int commentId);
    Comment getCommentByCommentId(int commentId);

}
