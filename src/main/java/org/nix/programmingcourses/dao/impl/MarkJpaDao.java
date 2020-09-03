package org.nix.programmingcourses.dao.impl;

import org.nix.programmingcourses.dao.MarkDao;
import org.nix.programmingcourses.entity.Mark;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class MarkJpaDao extends AbstractJpaDao<Mark> implements MarkDao {

    public MarkJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Mark> getById(long id) {
        return Optional.ofNullable(entityManager.find(Mark.class, id));
    }

    @Override
    public List<Mark> getAll() {
        TypedQuery<Mark> query = entityManager.createQuery("SELECT m FROM Mark m", Mark.class);
        return query.getResultList();
    }

}
