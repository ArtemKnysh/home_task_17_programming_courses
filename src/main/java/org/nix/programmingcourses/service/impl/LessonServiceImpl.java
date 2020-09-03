package org.nix.programmingcourses.service.impl;

import org.nix.programmingcourses.dao.LessonDao;
import org.nix.programmingcourses.entity.Group;
import org.nix.programmingcourses.entity.Lesson;
import org.nix.programmingcourses.entity.Student;
import org.nix.programmingcourses.service.LessonService;
import org.nix.programmingcourses.service.StudentService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class LessonServiceImpl extends AbstractServiceImpl<Lesson> implements LessonService {

    private final LessonDao lessonDao;
    private final StudentService studentService;

    public LessonServiceImpl(LessonDao lessonDao, StudentService studentService) {
        super(lessonDao);
        this.lessonDao = lessonDao;
        this.studentService = studentService;
    }

    @Override
    public Optional<Lesson> findNearestLessonByStudentId(long studentId) {
        Optional<Student> student = studentService.findById(studentId);
        if (student.isPresent()) {
            Group group = student.get().getGroup();
            return findLessonByGroupIdAndLessonDateTimeAfterOrderByLessonDateTimeAsc(
                    group.getId(), Instant.now()
            );
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Lesson> findAllLessonByThemeIdAndTeacherId(long themeId, long teacherId) {
        return lessonDao.getAllLessonByThemeIdAndTeacherId(themeId, teacherId);
    }

    @Override
    public Optional<Lesson> findLessonByGroupIdAndLessonDateTimeAfterOrderByLessonDateTimeAsc(long groupId, Instant dateTime) {
        return lessonDao.getLessonByGroupIdAndLessonDateTimeAfterOrderByLessonDateTimeAsc(groupId, dateTime);
    }
}
