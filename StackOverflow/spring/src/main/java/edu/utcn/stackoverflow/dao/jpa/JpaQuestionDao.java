package edu.utcn.stackoverflow.dao.jpa;

import edu.utcn.stackoverflow.dao.QuestionDao;
import edu.utcn.stackoverflow.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaQuestionDao extends QuestionDao, JpaRepository<Question, Long> {
}
