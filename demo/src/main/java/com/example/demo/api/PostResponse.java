package com.example.demo.api;

import java.util.List;

public class PostResponse {
    private int postID;
    private String postBody;
    private String date;
    private List<CommentResponse> comments;

    public PostResponse(int postID, String postBody, String date, List<CommentResponse> comments) {
        this.postID = postID;
        this.postBody = postBody;
        this.date = date;
        this.comments = comments;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
