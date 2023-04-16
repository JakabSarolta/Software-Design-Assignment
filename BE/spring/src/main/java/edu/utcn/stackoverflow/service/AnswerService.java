package edu.utcn.stackoverflow.service;

import edu.utcn.stackoverflow.dao.AnswerDao;
import edu.utcn.stackoverflow.model.Answer;
import edu.utcn.stackoverflow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AnswerService {
    @Autowired
    private AnswerDao answerDao;

    public Collection<Answer> getAllAnswers() {
        return answerDao.findAll();
    }

    public Answer getAnswerById(Long id) {
        return answerDao.getById(id);
    }

    public Answer createAnswer(Answer answer) {
        return answerDao.saveAndFlush(answer);
    }

    public void deleteAnswer(Long id) {
        answerDao.delete(this.getAnswerById(id));
    }
}
