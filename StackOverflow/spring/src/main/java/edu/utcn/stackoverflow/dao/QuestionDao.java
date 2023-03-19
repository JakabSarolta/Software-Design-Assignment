package edu.utcn.stackoverflow.dao;

import edu.utcn.stackoverflow.model.Question;
import edu.utcn.stackoverflow.model.Tag;
import edu.utcn.stackoverflow.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface QuestionDao extends Dao<Question> {
    Collection<Question> getQuestionsByTags(Tag tag);

    Collection<Question> getQuestionsByAuthor(User user);

    @Query("SELECT e FROM Question e WHERE e.title LIKE %:keyword%")
    Collection<Question> getQuestionByTitleKeyword(@Param("keyword") String keyword);
}
