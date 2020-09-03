package org.nix.programmingcourses.service;

import org.nix.programmingcourses.entity.Lesson;
import org.nix.programmingcourses.entity.Student;

public interface StudentService extends AbstractService<Student> {

    void showLessonInfoForStudent(Lesson lesson, Student student);

}
