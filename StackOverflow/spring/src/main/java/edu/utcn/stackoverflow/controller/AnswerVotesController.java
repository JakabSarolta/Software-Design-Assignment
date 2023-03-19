package edu.utcn.stackoverflow.controller;

import edu.utcn.stackoverflow.controller.dto.AnswerVoteInDto;
import edu.utcn.stackoverflow.controller.dto.AnswerVoteOutDto;
import edu.utcn.stackoverflow.controller.mapper.AnswerVoteMapper;
import edu.utcn.stackoverflow.model.AnswerVote;
import edu.utcn.stackoverflow.service.AnswerService;
import edu.utcn.stackoverflow.service.AnswerVoteService;
import edu.utcn.stackoverflow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/answervotes")
public class AnswerVotesController {
    @Autowired
    private AnswerVoteService answerVoteService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerVoteMapper answerVoteMapper;

    @PostMapping
    public AnswerVoteOutDto addAnswerVote(@RequestBody AnswerVoteInDto answerVoteInDto) {
        AnswerVote answerVote = answerVoteMapper.answerVoteFromDto(answerVoteInDto);
        answerVote.setAuthor(userService.findByUserName(answerVoteInDto.getAuthor()));
        answerVote.setAnswer(answerService.getAnswerById(answerVoteInDto.getAnswer()));
        AnswerVote answerVote1 = answerVoteService.createAnswerVote(answerVote);
        answerVote1.getAnswer().getAnswerVotes().add(answerVote1);
        answerVote1.getAuthor().getAnswerVotes().add(answerVote1);
        AnswerVoteOutDto answerVoteOutDto = answerVoteMapper.dtoFromAnswerVote(answerVote1);
        return answerVoteOutDto;
    }
}
