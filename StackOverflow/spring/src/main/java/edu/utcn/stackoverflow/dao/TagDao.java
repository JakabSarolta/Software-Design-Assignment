package edu.utcn.stackoverflow.dao;

import edu.utcn.stackoverflow.model.Tag;

public interface TagDao extends Dao<Tag> {
    public Tag findByName(String name);
}
