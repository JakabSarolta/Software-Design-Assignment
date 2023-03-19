package edu.utcn.stackoverflow.dao;

import edu.utcn.stackoverflow.model.BaseEntity;

import java.util.Collection;

public interface Dao<T extends BaseEntity> {
    Collection<T> findAll();

    T getById(Long id);

    T saveAndFlush(T entity);

    void delete(T entity);
}
