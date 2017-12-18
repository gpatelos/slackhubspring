package com.codestranodestra.slackhub.spring.slackhubspring.dao;

import com.codestranodestra.slackhub.spring.slackhubspring.model.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
@Qualifier("fakeMessage")
public class FakeMessageDao implements MessageDao {

    static Map<Integer, Message> messageMap;

    static {

        messageMap = new HashMap<Integer, Message>() {

            {
                put(1, new Message("Normally, both your asses would be dead as fucking fried chicken, " +
                        "but you happen to pull this shit while I'm in a transitional period so I don't wanna kill you, I wanna" +
                        " help you. But I can't give you this case, it don't belong to me. Besides, I've already been through " +
                        "too much shit this morning over this case to hand it over to your dumb ass.", 1));

                put(2, new Message("Now that there is the Tec-9, a crappy spray gun from South Miami. " +
                        "This gun is advertised as the most popular gun in American crime. Do you believe that shit? It actually " +
                        "says that in the little book that comes with it: the most popular gun in American crime. Like they're " +
                        "actually proud of that shit. ", 2));
                put(3, new Message("My money's in that office, right? If she start giving me some " +
                        "bullshit about it ain't there, and we got to go someplace else and get it, I'm gonna shoot you in the " +
                        "head then and there. Then I'm gonna shoot that bitch in the kneecaps, find out where my goddamn money " +
                        "is. She gonna tell me too. Hey, look at me when I'm talking to you, motherfucker. You listen: we go in " +
                        "there, and that nigga Winston or anybody else is in there, you the first motherfucker to get shot. You " +
                        "understand?", 3));
                put(4, new Message("You think water moves fast? You should see ice. It moves like it " +
                        "has a mind. Like it knows it killed the world once and got a taste for murder. After the avalanche, it " +
                        "took us a week to climb out. Now, I don't know exactly when we turned on each other, but I know that " +
                        "seven of us survived the slide... and only five made it out. Now we took an oath, that I'm breaking" +
                        " now. We said we'd say it was the snow that killed the other two, but it wasn't. Nature is lethal but" +
                        " it doesn't hold a candle to man.", 4));

            }

        };
    }

    @Override
    public Collection<Message> getAllMessages() {
        return this.messageMap.values();
    }

    @Override
    public Message getMessageById(Integer id) {
        return this.messageMap.get(id);
    }

    @Override
    public void removeMesssageById(Integer id) {
        this.messageMap.remove(id);

    }

    @Override
    public void updateMessage(Message message) {
        Message messageToUpdate = messageMap.get(message.getMessageId());
        messageToUpdate.setMessageBody(message.getMessageBody());
        messageToUpdate.setUserId(message.getUserId());
        messageMap.put(message.getMessageId(), messageToUpdate);
    }

    @Override
    public void insertMessageToDb(Message message) {
        messageMap.put(message.getMessageId(), message);
    }
}
