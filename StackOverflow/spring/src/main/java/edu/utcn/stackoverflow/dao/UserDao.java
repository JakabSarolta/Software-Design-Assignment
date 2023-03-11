package edu.utcn.stackoverflow.dao;

import edu.utcn.stackoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserDao extends Dao<User> {
    User findByUserName(String userName);

    User findByEmail(String email);
}
