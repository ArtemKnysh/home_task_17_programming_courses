package org.nix.programmingcourses.dao.impl;

import org.nix.programmingcourses.dao.GroupDao;
import org.nix.programmingcourses.entity.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class GroupJpaDao extends AbstractJpaDao<Group> implements GroupDao {

    public GroupJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Group> getById(long id) {
        return Optional.ofNullable(entityManager.find(Group.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Group> getAll() {
        TypedQuery<Group> query = entityManager.createQuery("SELECT g FROM Group g", Group.class);
        return query.getResultList();
    }

}
