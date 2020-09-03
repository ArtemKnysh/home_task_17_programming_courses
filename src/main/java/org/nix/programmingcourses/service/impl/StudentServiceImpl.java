package org.nix.programmingcourses.service.impl;

import org.nix.programmingcourses.dao.StudentDao;
import org.nix.programmingcourses.entity.Lesson;
import org.nix.programmingcourses.entity.Student;
import org.nix.programmingcourses.service.StudentService;

public class StudentServiceImpl extends AbstractServiceImpl<Student> implements StudentService {

    public StudentServiceImpl(StudentDao studentDao) {
        super(studentDao);
    }

    @Override
    public void showLessonInfoForStudent(Lesson lesson, Student student) {
        System.out.println(
                "LessonDateTime - " + lesson.getLessonDateTime() +
                "\nTeacher - " + lesson.getTeacher().getPersonData().getFullName() +
                "\nTheme - " + lesson.getTheme().getName()
        );
    }
}
