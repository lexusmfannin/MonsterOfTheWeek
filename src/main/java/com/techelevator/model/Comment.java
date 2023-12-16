package com.techelevator.model;

import java.util.Date;

public class Comment {
    private int userId;
    private String commentBox;
    private String commentTitle;
    private int commentId;
    private Date timestamp;

    public Comment(int userId, String commentBox, String commentTitle, int commentId, Date timestamp) {
        this.userId = userId;
        this.commentBox = commentBox;
        this.commentTitle = commentTitle;
        this.commentId = commentId;
        this.timestamp = timestamp;
    }
    public Comment(){};

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCommentBox() {
        return commentBox;
    }

    public void setCommentBox(String commentBox) {
        this.commentBox = commentBox;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
