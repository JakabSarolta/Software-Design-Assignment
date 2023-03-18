package edu.utcn.stackoverflow.service;

import edu.utcn.stackoverflow.dao.TagDao;
import edu.utcn.stackoverflow.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    private TagDao tagDao;

    public Tag findByName(String name) {
        return tagDao.findByName(name);
    }

    public Tag createTag(Tag tag) {
        return tagDao.saveAndFlush(tag);
    }
}
