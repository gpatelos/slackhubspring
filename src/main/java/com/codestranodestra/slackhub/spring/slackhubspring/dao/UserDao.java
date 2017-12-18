package com.codestranodestra.slackhub.spring.slackhubspring.dao;

import com.codestranodestra.slackhub.spring.slackhubspring.model.User;

import java.util.Collection;

public interface UserDao {
    Collection<User> getAllUsers();

    User getUserById(Integer id);

    void removeUserById(Integer id);

    void updateUser(User user);

    void insertUserToDb(User user);

}
