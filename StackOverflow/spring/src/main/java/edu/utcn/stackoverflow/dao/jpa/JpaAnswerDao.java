package edu.utcn.stackoverflow.dao.jpa;

import edu.utcn.stackoverflow.dao.AnswerDao;
import edu.utcn.stackoverflow.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAnswerDao extends AnswerDao, JpaRepository<Answer, Long> {
}
