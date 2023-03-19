package edu.utcn.stackoverflow.service;

import edu.utcn.stackoverflow.dao.QuestionDao;
import edu.utcn.stackoverflow.dao.jpa.JpaQuestionDao;
import edu.utcn.stackoverflow.model.Question;
import edu.utcn.stackoverflow.model.Tag;
import edu.utcn.stackoverflow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private JpaQuestionDao jpaQuestionDao;

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

    public Collection<Question> getQuestionsStartingFromIndexSortedByDateDesc(int index, int size) {
        Pageable pageable = PageRequest.of(index, size, Sort.by("date").descending());
        Page<Question> page = jpaQuestionDao.findAllByOrderByDateDesc(pageable);
        return page.getContent();
    }
}
