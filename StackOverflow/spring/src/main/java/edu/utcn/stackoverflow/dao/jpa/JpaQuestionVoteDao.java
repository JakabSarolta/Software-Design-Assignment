package edu.utcn.stackoverflow.dao.jpa;

import edu.utcn.stackoverflow.dao.QuestionVoteDao;
import edu.utcn.stackoverflow.model.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaQuestionVoteDao extends QuestionVoteDao, JpaRepository<QuestionVote, Long> {
}
