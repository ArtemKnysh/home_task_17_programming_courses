package org.nix.programmingcourses.dao.impl;

import org.nix.programmingcourses.dao.CourseDao;
import org.nix.programmingcourses.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CourseJpaDao extends AbstractJpaDao<Course> implements CourseDao {

    public CourseJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Course> getById(long id) {
        return Optional.ofNullable(entityManager.find(Course.class, id));
    }

    @Override
    public List<Course> getAll() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
        return query.getResultList();
    }

}
