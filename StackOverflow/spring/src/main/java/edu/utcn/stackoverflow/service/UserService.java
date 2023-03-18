package edu.utcn.stackoverflow.service;

import edu.utcn.stackoverflow.controller.NotFoundException;
import edu.utcn.stackoverflow.dao.UserDao;
import edu.utcn.stackoverflow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Collection<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(Long id) {
        return userDao.getById(id);
    }

    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User createUser(User user) {
        return userDao.saveAndFlush(user);
    }

    public User updateUser(User user) {
        return userDao.saveAndFlush(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }
}
