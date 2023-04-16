package edu.utcn.stackoverflow.controller;

import edu.utcn.stackoverflow.controller.dto.UserInDto;
import edu.utcn.stackoverflow.controller.dto.UserOutDto;
import edu.utcn.stackoverflow.controller.mapper.UserMapper;
import edu.utcn.stackoverflow.model.*;
import edu.utcn.stackoverflow.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final Boolean sendEmail = false;
    private final Boolean sendSms = false;

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;

    private Boolean validateUserName(String userName) {
        return userService.findByUserName(userName) == null;
    }

    private Boolean validateEmail(String email) {
        return userService.findByEmail(email) == null;
    }

    @GetMapping
    public Collection<UserOutDto> getAllUsers() {
        return userMapper.dtosFromUsers(userService.getAllUsers()); //map to outdto
    }

    @GetMapping("/{id}")
    public UserOutDto getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NotFoundException();
        }
        return userMapper.dtoFromUser(user);
    }

    @GetMapping("/username/{userName}")
    public UserOutDto getUserByUserName(@PathVariable("userName") String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            throw new BadRequestException();
        }
        return userMapper.dtoFromUser(user);
    }

    @GetMapping("/banned")
    public Collection<UserOutDto> getBannedUsers() {
        return userMapper.dtosFromUsers(userService.findBannedUsers());
    }

    @PostMapping
    public UserOutDto saveUser(@RequestBody @Valid UserInDto userInDto) {
        User user = userMapper.userFromDto(userInDto);

        Boolean validUserName = validateUserName(user.getUserName());
        Boolean validEmail = validateEmail(user.getEmail());

        if (!(validUserName && validEmail)) {
            throw new BadRequestException();
        }

        user.setQuestionVotes(new ArrayList<>());
        user.setAnswerVotes(new ArrayList<>());
        user.setScore((float) 0);
        user.setBanned(0);
        // user.setQuestions(new ArrayList<>());

        User user2 = userService.createUser(user);
        return userMapper.dtoFromUser(user2);
    }

    @PutMapping("/{id}")
    public UserOutDto updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserInDto userInDto) {
        if (userService.getUserById(id) == null) {
            throw new NotFoundException();
        }
        User user = userMapper.userFromDto(userInDto);
        user.setId(id);
        User userToBeReplaced = userService.getUserById(id);
        user.setQuestionVotes(userToBeReplaced.getQuestionVotes());
        user.setAnswerVotes(userToBeReplaced.getAnswerVotes());
        user.setScore(userToBeReplaced.getScore());
        user.setBanned(userToBeReplaced.getBanned());
        // user.setQuestions(userToBeReplaced.getQuestions());
        User user2 = userService.updateUser(user);
        return userMapper.dtoFromUser(user2);
    }

    @PatchMapping("/{id}/ban")
    public void banUser(@PathVariable("id") Long id) {
        if (userService.getUserById(id) == null) {
            throw new NotFoundException();
        }
        // SMS, email
        if (sendEmail)
            emailService.sendEmail(userService.getUserById(id).getEmail(), "You have been banned from StackOverflow", "You have been banned from StackOverflow!");
        if (sendSms)
            smsService.sendSms(("+4" + userService.getUserById(id).getPhoneNumber()), "You have been banned from StackOverflow!");

        User user = userService.getUserById(id);
        user.setBanned(1);
        userService.updateUser(user);
    }

    @PatchMapping("/{id}/unban")
    public void unbanUser(@PathVariable("id") String userName) {
        if (userService.findByUserName(userName) == null) {
            throw new NotFoundException();
        }
        User user = userService.findByUserName(userName);
        user.setBanned(0);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        if (userService.getUserById(id) == null) {
            throw new NotFoundException();
        }
        userService.deleteUser(userService.getUserById(id));
    }
}
