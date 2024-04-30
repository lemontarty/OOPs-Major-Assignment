package com.example.demo.api;

public class UserResponse {
    private String name;
    private Integer userID;
    private String email;

    public UserResponse(String name, Integer userID, String email) {
        this.name = name;
        this.userID = userID;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

