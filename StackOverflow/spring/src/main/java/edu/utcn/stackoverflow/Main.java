package edu.utcn.stackoverflow;

import edu.utcn.stackoverflow.dao.UserDao;
import edu.utcn.stackoverflow.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    public CommandLineRunner runner() {
//        return args -> {
//            User user = new User(1L, "Popescu", "Ion", "ionpopescu", "ionpopescu@popmmail.com", "123456", 0);
//            userDao.saveAndFlush(user);
//            log.info("Users: " + userDao.findAll());
//        };
//    }
}
