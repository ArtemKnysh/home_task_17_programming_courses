package org.nix.programmingcourses.dao.impl;

import org.nix.programmingcourses.dao.TeacherDao;
import org.nix.programmingcourses.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class TeacherJpaDao extends AbstractJpaDao<Teacher> implements TeacherDao {

    public TeacherJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Teacher> getById(long id) {
        return Optional.ofNullable(entityManager.find(Teacher.class, id));
    }

    @Override
    public List<Teacher> getAll() {
        TypedQuery<Teacher> query = entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class);
        return query.getResultList();
    }

}
