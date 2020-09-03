package org.nix.programmingcourses.dao.impl;

import org.nix.programmingcourses.dao.StudentDao;
import org.nix.programmingcourses.entity.Group;
import org.nix.programmingcourses.entity.Lesson;
import org.nix.programmingcourses.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class StudentJpaDao extends AbstractJpaDao<Student> implements StudentDao {

    public StudentJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Student> getById(long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    @Override
    public List<Student> getAll() {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s",Student.class);
        return query.getResultList();
    }


}
