package edu.utcn.stackoverflow.dao.jpa;

import edu.utcn.stackoverflow.dao.TagDao;
import edu.utcn.stackoverflow.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTagDao extends TagDao, JpaRepository<Tag, Long> {
}
