package edu.utcn.stackoverflow.service;

import edu.utcn.stackoverflow.dao.QuestionVoteDao;
import edu.utcn.stackoverflow.model.QuestionVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionVoteService {
    @Autowired
    private QuestionVoteDao questionVoteDao;

    public QuestionVote addQuestionVote(QuestionVote questionVote) {
        return questionVoteDao.saveAndFlush(questionVote);
    }
}
