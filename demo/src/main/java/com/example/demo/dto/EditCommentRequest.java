package com.example.demo.dto;

public class EditCommentRequest {
    private String commentBody;
    private Integer commentID;

    public EditCommentRequest(String commentBody, Integer commentID)
    {
        this.commentBody = commentBody;
        this.commentID = commentID;
    }

    public String getNewCommentBody() {
        return commentBody;
    }
    public Integer getCommentId() {
        return commentID;
    }

    public void setNewCommentBody(String newCommentBody) {
        this.commentBody = newCommentBody;
    }


    public void setCommentId(Integer commentId) {
        this.commentID = commentId;
    }
}
