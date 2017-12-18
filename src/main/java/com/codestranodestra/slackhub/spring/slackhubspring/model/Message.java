package com.codestranodestra.slackhub.spring.slackhubspring.model;

import java.sql.Timestamp;
import java.util.Date;

public class Message {

    private Integer messageId;
    private String messageBody;
    private Date timeStamp;
    private Integer userId;

    private static Integer nextMessageId = 1;


    public Message(){
    }
    public Message(String messageBody, Integer userId) {
        this.messageBody = messageBody;
        this.userId = userId;

        this.timeStamp = new Timestamp(System.currentTimeMillis());
        this.messageId = nextMessageId;
        nextMessageId++;
    }


    public Integer getMessageId() {
        return messageId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public static Integer getNextMessageId() {
        return nextMessageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
