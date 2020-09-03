package org.nix.programmingcourses.service.impl;

import org.nix.programmingcourses.dao.TeacherDao;
import org.nix.programmingcourses.entity.Group;
import org.nix.programmingcourses.entity.Lesson;
import org.nix.programmingcourses.entity.Mark;
import org.nix.programmingcourses.entity.Teacher;
import org.nix.programmingcourses.service.LessonService;
import org.nix.programmingcourses.service.TeacherService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeacherServiceImpl extends AbstractServiceImpl<Teacher> implements TeacherService {

    private final LessonService lessonService;
    private final long finalExamThemId = 5;

    public TeacherServiceImpl(TeacherDao teacherDao, LessonService lessonService) {
        super(teacherDao);
        this.lessonService = lessonService;
    }

    @Override
    public Optional<Group> findMostSuccessfulGroupByTeacherId(long teacherId) {
        List<Lesson> finalExams = lessonService.findAllLessonByThemeIdAndTeacherId(finalExamThemId, teacherId);
        Optional<Lesson> largestMedian = finalExams
                .stream().max(
                        Comparator.comparingDouble(o -> calcMedianMark(o.getMarks()))
                );
        return largestMedian.map(Lesson::getGroup);
    }

    @Override
    public double calcMedianMark(List<Mark> marks) {
        List<Integer> sortedMarkValues = marks.stream()
                .map(Mark::getMarkValue)
                .sorted()
                .collect(Collectors.toList());        
        int n = sortedMarkValues.size();        
        if (n % 2 != 0) {
            return (double) sortedMarkValues.get(n / 2);
        }        
        return (double)(sortedMarkValues.get((n - 1) / 2) + sortedMarkValues.get(n / 2)) / 2.0;
    }
}