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
import java.util.Objects;

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
        log.info("----------------------------------------------------------------------------------------------------------------------");
        QuestionVote questionVote = questionVoteMapper.questionVoteFromDto(questionVoteInDto);
        questionVote.setAuthor(userService.findByUserName(questionVoteInDto.getAuthor()));
        questionVote.setQuestion(questionService.getQuestionById(questionVoteInDto.getQuestion()));
        QuestionVote questionVote1 = questionVoteService.addQuestionVote(questionVote);
        questionVote1.getQuestion().getQuestionVotes().add(questionVote1);
        questionVote1.getAuthor().getQuestionVotes().add(questionVote1);
        if (Objects.equals(questionVote1.getVoteType(), "up")) {
//            log.info("----------------------------------------------------------------------------------------------------------------------");
//            log.info("The user who should get the points is: " + questionVote1.getQuestion().getAuthor().getUserName());
//            log.info(questionVote1.getQuestion().getAuthor().getScore().toString());
            questionVote1.getQuestion().getAuthor().setScore((float) (questionVote1.getQuestion().getAuthor().getScore() + 2.5));
            userService.createUser(questionVote1.getQuestion().getAuthor());
//            log.info(questionVote1.getQuestion().getAuthor().getScore().toString());
        } else {
            questionVote1.getQuestion().getAuthor().setScore((float) (questionVote1.getQuestion().getAuthor().getScore() - 1.5));
            userService.createUser(questionVote1.getQuestion().getAuthor());
        }
        return questionVoteMapper.dtoFromQuestionVote(questionVote1);
    }
}
