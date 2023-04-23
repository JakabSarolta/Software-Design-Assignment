package edu.utcn.stackoverflow.service;

import edu.utcn.stackoverflow.dao.QuestionDao;
import edu.utcn.stackoverflow.dao.jpa.JpaQuestionDao;
import edu.utcn.stackoverflow.model.Question;
import edu.utcn.stackoverflow.model.Tag;
import edu.utcn.stackoverflow.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
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

    public Question getLastQuestion() {
        return questionDao.findTopByOrderByIdDesc();
    }

    public Question getFirstQuestion() {
        return questionDao.findTopByOrderByIdAsc();
    }

    public Collection<Question> findByIdLessThanEqualOrderByIdDesc(Long id, int size) {
        Collection<Question> questions = questionDao.findByIdLessThanEqualOrderByIdDesc(id);

//        return questions;

        List<Question> list = new ArrayList<>();

        int i = 0;
        for(Question q : questions) {
            if(i == size) {
                break;
            }
            list.add(q);
            i++;
        }
        return list;
    }

    public Collection<Question> findByIdGreaterThanEqual(Long id, int size) {
        Collection<Question> questions = questionDao.findByIdGreaterThanEqual(id);

        List<Question> list = new ArrayList<>();

        int i = 0;
        for(Question q : questions) {
            if(i == size) {
                break;
            }
            list.add(q);
            i++;
        }

        //reverse list
        List<Question> reversedList = new ArrayList<>();

        for(int j = list.size() - 1; j >= 0; j--) {
            reversedList.add(list.get(j));
        }

        return reversedList;
    }
}
