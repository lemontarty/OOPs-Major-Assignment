package com.example.demo.api;

public class CommentCreatorResponse {
    private Integer userID;
    private String name;

    public CommentCreatorResponse(Integer userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
