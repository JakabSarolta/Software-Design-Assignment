package edu.utcn.stackoverflow.dao.jpa;

import edu.utcn.stackoverflow.dao.UserDao;
import edu.utcn.stackoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserDao extends UserDao, JpaRepository<User, Long> {

}
