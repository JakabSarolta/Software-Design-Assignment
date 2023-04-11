package edu.utcn.stackoverflow.service;

import edu.utcn.stackoverflow.dao.AnswerVoteDao;
import edu.utcn.stackoverflow.model.AnswerVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerVoteService {
    @Autowired
    private AnswerVoteDao answerVoteDao;

    public AnswerVote createAnswerVote(AnswerVote answerVote) {
        return answerVoteDao.saveAndFlush(answerVote);
    }
}
