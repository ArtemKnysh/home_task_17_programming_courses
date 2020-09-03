package org.nix.programmingcourses.service;

import org.nix.programmingcourses.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T extends AbstractEntity> {

    Optional<T> findById(long id);
    List<T> findAll();
    void save(T t);
    void update(T t);
    void remove(T t);
    void removeById(long id);
    void removeAll();

}
