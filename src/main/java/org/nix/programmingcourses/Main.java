package org.nix.programmingcourses;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.nix.programmingcourses.dao.LessonDao;
import org.nix.programmingcourses.dao.StudentDao;
import org.nix.programmingcourses.dao.TeacherDao;
import org.nix.programmingcourses.dao.impl.LessonJpaDao;
import org.nix.programmingcourses.dao.impl.StudentJpaDao;
import org.nix.programmingcourses.dao.impl.TeacherJpaDao;
import org.nix.programmingcourses.entity.Group;
import org.nix.programmingcourses.entity.Teacher;
import org.nix.programmingcourses.service.LessonService;
import org.nix.programmingcourses.service.StudentService;
import org.nix.programmingcourses.service.TeacherService;
import org.nix.programmingcourses.service.impl.LessonServiceImpl;
import org.nix.programmingcourses.service.impl.StudentServiceImpl;
import org.nix.programmingcourses.service.impl.TeacherServiceImpl;

import javax.persistence.EntityManager;
import java.util.Objects;
import java.util.Optional;


public class Main {

    public static void main(String[] args) {
        Configuration cfg = getConfiguration();
        EntityManager entityManager = null;
        try (SessionFactory sessionFactory = cfg.buildSessionFactory()) {
            entityManager = sessionFactory.createEntityManager();
            TeacherDao teacherDao = new TeacherJpaDao(entityManager);
            LessonDao lessonDao = new LessonJpaDao(entityManager);
            StudentDao studentDao = new StudentJpaDao(entityManager);
            StudentService studentService = new StudentServiceImpl(studentDao);
            LessonService lessonService = new LessonServiceImpl(lessonDao, studentService);
            TeacherService teacherService = new TeacherServiceImpl(teacherDao, lessonService);
            Teacher teacher;
            if (teacherService.findById(2).isEmpty()) {
                return;
            } else {
                teacher = teacherService.findById(2).get();
            }
            Optional<Group> mostSuccessfulGroup = teacherService.findMostSuccessfulGroupByTeacherId(teacher.getId());
            if (mostSuccessfulGroup.isPresent()) {
                Group group = mostSuccessfulGroup.get();
                System.out.println("Most successful group of teacher " + teacher.getPersonData().getFullName() + ":");
                System.out.println(
                        "Name: " + group.getName() +
                        "\nCourse: " + group.getCourse().getName());
            }
        } catch (Exception e) {
            System.err.println("Error when populating db: " + e.getMessage());
            Objects.requireNonNull(entityManager).close();
            e.printStackTrace();
        }
    }

    public static Configuration getConfiguration() {
        Configuration cfg = new Configuration();
        cfg.configure();
        return cfg;
    }
}
