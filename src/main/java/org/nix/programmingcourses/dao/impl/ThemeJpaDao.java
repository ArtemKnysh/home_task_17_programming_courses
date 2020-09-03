package org.nix.programmingcourses.dao.impl;

import org.nix.programmingcourses.dao.ThemeDao;
import org.nix.programmingcourses.entity.Theme;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ThemeJpaDao extends AbstractJpaDao<Theme> implements ThemeDao {

    public ThemeJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Theme> getById(long id) {
        return Optional.ofNullable(entityManager.find(Theme.class, id));
    }

    @Override
    public List<Theme> getAll() {
        TypedQuery<Theme> query = entityManager.createQuery("SELECT t FROM Theme t", Theme.class);
        return query.getResultList();
    }

}
