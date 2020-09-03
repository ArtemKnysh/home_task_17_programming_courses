package org.nix.programmingcourses.service.impl;

import org.nix.programmingcourses.dao.AbstractDao;
import org.nix.programmingcourses.entity.AbstractEntity;
import org.nix.programmingcourses.service.AbstractService;

import java.util.List;
import java.util.Optional;

public class AbstractServiceImpl<T extends AbstractEntity>  implements AbstractService<T> {

    protected AbstractDao<T> entityDao;

    public AbstractServiceImpl(AbstractDao<T> entityDao) {
        this.entityDao = entityDao;
    }

    @Override
    public Optional<T> findById(long id) {
        return entityDao.getById(id);
    }

    @Override
    public List<T> findAll() {
        return entityDao.getAll();
    }

    @Override
    public void save(T t) {
        entityDao.save(t);
    }

    @Override
    public void update(T t) {
        entityDao.update(t);
    }

    @Override
    public void remove(T t) {
        entityDao.delete(t);
    }

    @Override
    public void removeById(long id) {
        entityDao.deleteById(id);
    }

    @Override
    public void removeAll() {
        entityDao.deleteAll();
    }

}
