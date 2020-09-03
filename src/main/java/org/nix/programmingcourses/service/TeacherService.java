package org.nix.programmingcourses.service;

import org.nix.programmingcourses.entity.Group;
import org.nix.programmingcourses.entity.Mark;
import org.nix.programmingcourses.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService extends AbstractService<Teacher> {

    Optional<Group> findMostSuccessfulGroupByTeacherId(long teacherId);

    double calcMedianMark(List<Mark> marks);
}
