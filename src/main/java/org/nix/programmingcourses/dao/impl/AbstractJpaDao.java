package org.nix.programmingcourses.dao.impl;

import org.nix.programmingcourses.dao.AbstractDao;
import org.nix.programmingcourses.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractJpaDao<T extends AbstractEntity> implements AbstractDao<T> {

    protected EntityManager entityManager;

    public AbstractJpaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }


    @Override
    public void save(T t) {
        executeInsideTransaction(manger -> manger.persist(t));
    }

    @Override
    public void update(T t) {
        executeInsideTransaction(manger -> manger.merge(t));
    }

    @Override
    public void delete(T t) {
        executeInsideTransaction(manger -> manger.remove(t));
    }

    @Override
    public void deleteById(long id) {
        Optional<T> entity = getById(id);
        entity.ifPresent(this::delete);
    }

    @Override
    public void deleteAll() {
        List<T> entityList = getAll();
        for (T entity : entityList) {
            delete(entity);
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
