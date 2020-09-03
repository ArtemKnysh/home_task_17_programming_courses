package org.nix.programmingcourses.dao.impl;

import org.nix.programmingcourses.dao.LessonDao;
import org.nix.programmingcourses.entity.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class LessonJpaDao extends AbstractJpaDao<Lesson> implements LessonDao {

    public LessonJpaDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Lesson> getById(long id) {
        return Optional.ofNullable(entityManager.find(Lesson.class, id));
    }

    @Override
    public List<Lesson> getAll() {
        TypedQuery<Lesson> query = entityManager.createQuery("SELECT l FROM Lesson l", Lesson.class);
        return query.getResultList();
    }

    @Override
    public Optional<Lesson> getLessonByGroupIdAndLessonDateTimeAfterOrderByLessonDateTimeAsc(long groupId, Instant dateTime) {
        TypedQuery<Lesson> query = entityManager.createQuery("SELECT l from Lesson l " +
                "where l.group.id =: groupId and l.lessonDateTime > :nowDateAndTime " +
                "order by l.lessonDateTime asc", Lesson.class)
                .setParameter("groupId", groupId)
                .setParameter("nowDateAndTime", new Timestamp(dateTime.toEpochMilli()))
                .setMaxResults(1);
        return Optional.ofNullable(query.getResultList().get(0));
    }

    @Override
    public List<Lesson> getAllLessonByThemeIdAndTeacherId(long themeId, long teacherId) {
        TypedQuery<Lesson> query = entityManager.createQuery("SELECT l from Lesson l " +
                "where l.theme.id = :themeId and l.teacher.id = :teacherId ", Lesson.class)
                .setParameter("themeId", themeId)
                .setParameter("teacherId", teacherId);
        return query.getResultList();
    }
}
