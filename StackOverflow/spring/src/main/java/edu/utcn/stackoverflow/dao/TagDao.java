package edu.utcn.stackoverflow.dao;

import edu.utcn.stackoverflow.model.Tag;

public interface TagDao extends Dao<Tag> {
    Tag findByName(String name);
}
