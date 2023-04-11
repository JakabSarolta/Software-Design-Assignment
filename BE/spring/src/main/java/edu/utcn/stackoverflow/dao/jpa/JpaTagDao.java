package edu.utcn.stackoverflow.dao.jpa;

import edu.utcn.stackoverflow.dao.TagDao;
import edu.utcn.stackoverflow.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTagDao extends TagDao, JpaRepository<Tag, Long> {
}
