package org.nix.programmingcourses.service;

import org.nix.programmingcourses.entity.Lesson;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface LessonService extends AbstractService<Lesson> {

    Optional<Lesson> findLessonByGroupIdAndLessonDateTimeAfterOrderByLessonDateTimeAsc(long groupId, Instant dateTime);

    Optional<Lesson> findNearestLessonByStudentId(long studentId);

    List<Lesson> findAllLessonByThemeIdAndTeacherId(long themeId, long teacherId);
}
