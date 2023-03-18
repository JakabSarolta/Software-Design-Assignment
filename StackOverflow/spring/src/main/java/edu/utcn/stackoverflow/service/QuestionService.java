package edu.utcn.stackoverflow.service;

import edu.utcn.stackoverflow.dao.QuestionDao;
import edu.utcn.stackoverflow.model.Question;
import edu.utcn.stackoverflow.model.Tag;
import edu.utcn.stackoverflow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public Collection<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionDao.getById(id);
    }

    public Question createQuestion(Question question) {
        return questionDao.saveAndFlush(question);
    }

    public Question updateQuestion(Question question) {
        return questionDao.saveAndFlush(question);
    }

    public void deleteQuestion(Question question) {
        questionDao.delete(question);
    }

    public Collection<Question> getQuestionsByTag(Tag tag) {
        return questionDao.getQuestionsByTags(tag);
    }

    public Collection<Question> getQuestionsByAuthor(User user) {
        return questionDao.getQuestionsByAuthor(user);
    }

    public Collection<Question> getQuestionsByTitleKeyword(String keyword) {
        return questionDao.getQuestionByTitleKeyword(keyword);
    }
}
