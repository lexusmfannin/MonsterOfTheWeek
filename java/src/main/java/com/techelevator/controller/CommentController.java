package com.techelevator.controller;


import com.techelevator.dao.CommentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/social")
@PreAuthorize("isAuthenticated()")
public class CommentController {

    private CommentDao commentDao;
    private UserDao userDao;

    public CommentController(CommentDao commentDao, UserDao userDao) {
        this.commentDao = commentDao;
        this.userDao = userDao;

    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Comment> getComments() {
        try {
            return commentDao.getAllComments();
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/addComment", method = RequestMethod.POST)
    public Comment createComment(@Valid @RequestBody Comment comment, Principal principal) {
        try {
            return commentDao.createComment(comment, getCurrentUserId(principal));
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{user_id}/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment updateComment(@PathVariable int userId, @PathVariable int commentId, @Valid @RequestBody Comment updatedComment) {
        updatedComment.setCommentId(commentId);

        try {
            return commentDao.updateComment(updatedComment);
        } catch (DaoException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating comment", e);
        }
    }
    @DeleteMapping("/{user_id}/{comment_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable int userId, @PathVariable int commentId) {
        try {
            commentDao.deleteComment(userId, commentId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting comment", e);
        }
    }

    @RequestMapping(value = "/{comment_id}", method = RequestMethod.GET)
    public Comment getCommentByCommentId(@PathVariable int commentId) {
        try {
            return commentDao.getCommentByCommentId(commentId);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int getCurrentUserId(Principal principal){
        return userDao.getUserByUsername(principal.getName()).getId();
    }
}
