package com.example.demo.dto;

public class CreatePostRequest {
    private String postBody;
    private Integer userID;

    public CreatePostRequest() {
    }

    public CreatePostRequest(String postBody, Integer userID) {
        this.postBody = postBody;
        this.userID = userID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}

