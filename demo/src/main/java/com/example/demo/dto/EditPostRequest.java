package com.example.demo.dto;

public class EditPostRequest {
    private String postBody;
    private Integer postID;

    // Constructor, getters, and setters
    // Constructor
    public EditPostRequest(String postBody, Integer postID) {
        this.postBody = postBody;
        this.postID = postID;
    }

    // Getters and setters
    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }
}