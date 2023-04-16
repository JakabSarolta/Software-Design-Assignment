package edu.utcn.stackoverflow.dao;

import edu.utcn.stackoverflow.model.User;

import java.util.Collection;

public interface UserDao extends Dao<User> {
    User findByUserName(String userName);

    User findByEmail(String email);

    Collection<User> findByBanned(Integer banned);
}
