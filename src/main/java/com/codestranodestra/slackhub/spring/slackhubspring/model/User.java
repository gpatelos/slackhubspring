package com.codestranodestra.slackhub.spring.slackhubspring.model;

public class User {

    private Integer userId;
    private String name;
    private String userName;
    private String password;

    private static Integer nextUserId= 1;

    public User(){

    }

    public User(String name) {
        this.name=name;
        this.userId = nextUserId;
        nextUserId++;

    }

    public Integer getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Integer getNextUserId() {
        return nextUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}