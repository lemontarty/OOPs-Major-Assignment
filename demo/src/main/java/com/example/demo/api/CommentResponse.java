package com.example.demo.api;

import com.example.demo.model.User;

public class CommentResponse {
    private int commentID;
    private String commentBody;
    private CommentCreatorResponse commentCreator;

    public CommentResponse(int commentID, String commentBody, User commentCreator) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.commentCreator = new CommentCreatorResponse(commentCreator.getId(), commentCreator.getName());
    }

    public int getCommentID() {
        return commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public CommentCreatorResponse getCommentCreator() {
        return commentCreator;
    }
}
