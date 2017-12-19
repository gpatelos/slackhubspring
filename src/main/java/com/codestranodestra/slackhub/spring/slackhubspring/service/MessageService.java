package com.codestranodestra.slackhub.spring.slackhubspring.service;

import com.codestranodestra.slackhub.spring.slackhubspring.dao.MessageDao;
import com.codestranodestra.slackhub.spring.slackhubspring.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageService {

    @Autowired
    @Qualifier("fakeMessage")
    private MessageDao messageDao;

    public Collection<Message> getAllMessages() {
        return this.messageDao.getAllMessages();
    }

    public Message getMessageById(Integer id) {
        return this.messageDao.getMessageById(id);
    }

    public void deleteMessageById(Integer id) {
        this.messageDao.removeMesssageById(id);
    }

    public void updateMessage(Message message) {
        this.messageDao.updateMessage(message);
    }

    public void createMessge(Message message) {
        this.messageDao.insertMessageToDb(message);
    }

}
