package edu.utcn.stackoverflow.dao.jpa;

import edu.utcn.stackoverflow.dao.AnswerVoteDao;
import edu.utcn.stackoverflow.model.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAnswerVoteDao extends AnswerVoteDao, JpaRepository<AnswerVote, Long> {
}
