package org.nix.programmingcourses.dao;

import org.nix.programmingcourses.entity.Lesson;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface LessonDao extends AbstractDao<Lesson> {

    Optional<Lesson> getLessonByGroupIdAndLessonDateTimeAfterOrderByLessonDateTimeAsc(long groupId, Instant dateTime);

    List<Lesson> getAllLessonByThemeIdAndTeacherId(long themeId, long teacherId);
}
