package com.codestranodestra.slackhub.spring.slackhubspring.dao;

import com.codestranodestra.slackhub.spring.slackhubspring.model.Message;

import java.util.Collection;

public interface MessageDao {

    Collection<Message> getAllMessages();

    Message getMessageById(Integer id);

    void removeMesssageById(Integer id);

    void updateMessage(Message messsage);

    void insertMessageToDb(Message message);

}
