package edu.utcn.stackoverflow.controller;

import edu.utcn.stackoverflow.controller.dto.UserInDto;
import edu.utcn.stackoverflow.controller.dto.UserOutDto;
import edu.utcn.stackoverflow.controller.mapper.UserMapper;
import edu.utcn.stackoverflow.dao.UserDao;
import edu.utcn.stackoverflow.model.Question;
import edu.utcn.stackoverflow.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    private Boolean validateUserName(String userName){
        if(userDao.findByUserName(userName) != null){
            return false;
        }
        return true;
    }

    private Boolean validateEmail(String email){
        if(userDao.findByEmail(email) != null){
            return false;
        }
        return true;
    }

    @GetMapping
    public Collection<UserOutDto> getAllUsers() {
        Collection<UserOutDto> userOutDtos = userMapper.dtosFromUsers(userDao.findAll());
        return userOutDtos; //map to outdto
    }

    @GetMapping("/{id}")
    public UserOutDto getUserById(@PathVariable("id") Long id) {
        User user = userDao.getById(id);
        UserOutDto userOutDto = userMapper.dtoFromUser(user);
        return userOutDto;
    }

    @GetMapping("/username/{userName}")
    public UserOutDto getUserByUserName(@PathVariable("userName") String userName) {
        User user = userDao.findByUserName(userName);
        if(user == null){
            throw new BadRequestException();
        }
        UserOutDto userOutDto = userMapper.dtoFromUser(user);
        return userOutDto;
    }

    @PostMapping
    public UserOutDto saveUser(@RequestBody UserInDto userInDto) {
        User user = userMapper.userFromDto(userInDto);

        Boolean validUserName = validateUserName(user.getUserName());
        Boolean validEmail = validateEmail(user.getEmail());

        if(!(validUserName && validEmail)){
            throw new BadRequestException();
        }

        User user2 = userDao.saveAndFlush(user);
        UserOutDto userOutDto = userMapper.dtoFromUser(user2);
        return userOutDto;
    }

    @PutMapping("/{id}")
    public UserOutDto updateUser(@PathVariable("id") Long id, @RequestBody UserInDto userInDto) {
        if(userDao.getById(id) == null){
            throw new NotFoundException();
        }
        User user = userMapper.userFromDto(userInDto);
        user.setId(id);
        User userToBeReplaced = userDao.getById(id);
        Collection<Question> questions = userToBeReplaced.getQuestions();
        user.setQuestions(questions);
        User user2 = userDao.saveAndFlush(user);
        return userMapper.dtoFromUser(user2);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        if(userDao.getById(id) == null){
            throw new NotFoundException();
        }
        userDao.delete(userDao.getById(id));
    }
}
