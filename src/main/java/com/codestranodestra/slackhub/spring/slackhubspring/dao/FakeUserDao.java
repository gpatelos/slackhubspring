package com.codestranodestra.slackhub.spring.slackhubspring.dao;

import com.codestranodestra.slackhub.spring.slackhubspring.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeUser")
public class FakeUserDao implements UserDao {

    static Map<Integer, User> userMap;

    static {

        userMap = new HashMap<Integer, User>() {

            {
                put(1, new User("Pantha121"));
                put(2, new User("LotusBlossom43"));
                put(3, new User("SnokeLover72"));
                put(4, new User("MrsSnoke"));
            }
        };
    }


    @Override
    public Collection<User> getAllUsers() {
        return this.userMap.values();
    }

    @Override
    public User getUserById(Integer id) {
        return this.userMap.get(id);
    }

    @Override
    public void removeUserById(Integer id) {
        this.userMap.remove(id);
    }

    @Override
    public void updateUser(User user) {
        User updatedUser = userMap.get(user.getUserId());
        updatedUser.setUserName(user.getUserName());
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        userMap.put(user.getUserId(), updatedUser);
    }

    @Override
    public void insertUserToDb(User user) {
        userMap.put(user.getUserId(), user);
    }
}
