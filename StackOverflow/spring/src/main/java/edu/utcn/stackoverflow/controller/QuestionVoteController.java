package edu.utcn.stackoverflow.controller;

import edu.utcn.stackoverflow.controller.dto.QuestionVoteInDto;
import edu.utcn.stackoverflow.controller.dto.QuestionVoteOutDto;
import edu.utcn.stackoverflow.controller.mapper.QuestionVoteMapper;
import edu.utcn.stackoverflow.model.QuestionVote;
import edu.utcn.stackoverflow.service.QuestionService;
import edu.utcn.stackoverflow.service.QuestionVoteService;
import edu.utcn.stackoverflow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/questionvotes")
public class QuestionVoteController {
    @Autowired
    private QuestionVoteService questionVoteService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionVoteMapper questionVoteMapper;

    @PostMapping
    public QuestionVoteOutDto addQuestionVote(@RequestBody @Valid QuestionVoteInDto questionVoteInDto) {
        QuestionVote questionVote = questionVoteMapper.questionVoteFromDto(questionVoteInDto);
        questionVote.setAuthor(userService.findByUserName(questionVoteInDto.getAuthor()));
        questionVote.setQuestion(questionService.getQuestionById(questionVoteInDto.getQuestion()));
        QuestionVote questionVote1 = questionVoteService.addQuestionVote(questionVote);
        questionVote1.getQuestion().getQuestionVotes().add(questionVote1);
        questionVote1.getAuthor().getQuestionVotes().add(questionVote1);
        return questionVoteMapper.dtoFromQuestionVote(questionVote1);
    }
}
