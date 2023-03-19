package edu.utcn.stackoverflow.controller;

import edu.utcn.stackoverflow.controller.dto.AnswerInDto;
import edu.utcn.stackoverflow.controller.dto.AnswerOutDto;
import edu.utcn.stackoverflow.controller.mapper.AnswerMapper;
import edu.utcn.stackoverflow.model.Answer;
import edu.utcn.stackoverflow.service.AnswerService;
import edu.utcn.stackoverflow.service.QuestionService;
import edu.utcn.stackoverflow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public Collection<AnswerOutDto> getAllAnswers() {
        return answerMapper.dtosFromAnswers(answerService.getAllAnswers());
    }

    @GetMapping("/{id}")
    public AnswerOutDto getAnswerById(@PathVariable("id") Long id) {
        log.info("Answer with id {} was requested.", id);
        return answerMapper.dtoFromAnswer(answerService.getAnswerById(id));
    }

    @PostMapping
    public AnswerOutDto addAnswer(@RequestBody AnswerInDto answerInDto) {
        Answer answer = answerMapper.answerFromDto(answerInDto);
        answer.setAuthor(userService.findByUserName(answerInDto.getAuthor()));
        answer.setQuestion(questionService.getQuestionById(answerInDto.getQuestion()));
        answer.setAnswerVotes(new ArrayList<>());
        questionService.getQuestionById(answerInDto.getQuestion()).getAnswers().add(answer);
        Answer answer2 = answerService.createAnswer(answer);
        return answerMapper.dtoFromAnswer(answer2);
    }

    @PutMapping("/{id}")
    public AnswerOutDto updateAnswer(@PathVariable("id") Long id, @RequestBody AnswerInDto answerInDto) {
        Answer answer = answerMapper.answerFromDto(answerInDto);
        answer.setId(id);
        answer.setAuthor(userService.findByUserName(answerInDto.getAuthor()));
        answer.setQuestion(questionService.getQuestionById(answerInDto.getQuestion()));
        answer.setAnswerVotes(answerService.getAnswerById(id).getAnswerVotes());
        Answer answer2 = answerService.createAnswer(answer);
        return answerMapper.dtoFromAnswer(answer2);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable("id") Long id) {
        questionService.getQuestionById(answerService.getAnswerById(id).getQuestion().getId()).getAnswers().remove(answerService.getAnswerById(id));
        answerService.deleteAnswer(id);
    }
}
