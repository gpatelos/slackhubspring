package com.codestranodestra.slackhub.spring.slackhubspring.service;

import com.codestranodestra.slackhub.spring.slackhubspring.dao.UserDao;
import com.codestranodestra.slackhub.spring.slackhubspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    @Qualifier("fakeUser")
    private UserDao userDao;


    public Collection<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    public User getUserById(Integer id) {
        return this.userDao.getUserById(id);
    }

    public void deleteUserById(Integer id) {
        this.userDao.removeUserById(id);
    }

    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    public void createUser(User user) {
        this.userDao.insertUserToDb(user);
    }


}
