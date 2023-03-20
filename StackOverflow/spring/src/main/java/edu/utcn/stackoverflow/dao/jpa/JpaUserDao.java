package edu.utcn.stackoverflow.dao.jpa;

import edu.utcn.stackoverflow.dao.UserDao;
import edu.utcn.stackoverflow.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface JpaUserDao extends UserDao, JpaRepository<User, Long> {

}
