package edu.utcn.stackoverflow.dao;

import edu.utcn.stackoverflow.model.User;

public interface UserDao extends Dao<User> {
    User findByUserName(String userName);

    User findByEmail(String email);
}
