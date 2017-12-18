package com.codestranodestra.slackhub.spring.slackhubspring.dao;
import com.codestranodestra.slackhub.spring.slackhubspring.model.Message;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FakeMessageDaoTest {
    @Test
    public void updateMessageTest() throws Exception {

        FakeMessageDao fakeMessageDao = new FakeMessageDao();

        Message greg = new Message("Update this message", 99);
        fakeMessageDao.insertMessageToDb(greg);
        greg.setMessageBody("really updated for real");
        fakeMessageDao.updateMessage(greg);
        String expected = "really updated for real";

        Assert.assertEquals(expected, fakeMessageDao.getMessageById(5).getMessageBody());

    }

}