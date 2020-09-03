package org.nix.programmingcourses.dao;

import org.nix.programmingcourses.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface AbstractDao<T extends AbstractEntity> {

    Optional<T> getById(long id);
    List<T> getAll();
    void save(T t);
    void update(T t);
    void delete(T t);
    void deleteById(long id);
    void deleteAll();

}
