package org.nix.programmingcourses;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.nix.programmingcourses.dao.*;
import org.nix.programmingcourses.dao.impl.*;
import org.nix.programmingcourses.entity.*;
import org.nix.programmingcourses.service.LessonService;
import org.nix.programmingcourses.service.StudentService;
import org.nix.programmingcourses.service.impl.LessonServiceImpl;
import org.nix.programmingcourses.service.impl.StudentServiceImpl;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntitiesTest {

    static SessionFactory sessionFactory;
    static EntityManager entityManager;
    private static GroupDao groupDao;
    private static CourseDao courseDao;
    private static ThemeDao themeDao;
    private static TeacherDao teacherDao;
    private static LessonDao lessonDao;
    private static LessonService lessonService;
    private static StudentDao studentDao;
    private static StudentService studentService;

    @BeforeAll
    static void configure() {
        Configuration cfg = new Configuration().configure();
        sessionFactory = cfg.buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
        groupDao = new GroupJpaDao(entityManager);
        courseDao = new CourseJpaDao(entityManager);
        themeDao = new ThemeJpaDao(entityManager);
        teacherDao = new TeacherJpaDao(entityManager);
        lessonDao = new LessonJpaDao(entityManager);
        studentDao = new StudentJpaDao(entityManager);
        studentService = new StudentServiceImpl(studentDao);
        lessonService = new LessonServiceImpl(lessonDao, studentService);
    }

    @AfterAll
    static void closing() {
        entityManager.close();
        sessionFactory.close();
    }

    @Test
    public void entitiesTest() {
        Course course = new Course();
        course.setName("course name 1");
        courseDao.save(course);
        isEquals(courseDao, course);

        Group group = new Group();
        group.setName("group 1");
        group.setStartEduction(LocalDate.of(2020, 5, 22));
        group.setEndEduction(LocalDate.of(2020, 9, 27));
        course.addGroup(group);
        groupDao.save(group);
        assertTrue(course.getGroups().contains(group));
        isEquals(groupDao, group);

        Student student = new Student();
        student.setPersonData(new PersonData("first name 1", "last name 1", LocalDate.of(2000, 4, 20)));
        group.addStudent(student);
        studentDao.save(student);
        assertTrue(group.getStudents().contains(student));
        isEquals(studentDao, student);

        Teacher teacher = new Teacher();
        teacher.setPersonData(new PersonData("first name 2", "last name 2", LocalDate.of(1989, 2, 11)));
        teacherDao.save(teacher);
        isEquals(teacherDao, teacher);

        Theme theme = new Theme();
        theme.setName("theme 1");
        themeDao.save(theme);
        isEquals(themeDao, theme);

        Lesson firstLesson = new Lesson();
        firstLesson.setGroup(group);
        firstLesson.setTeacher(teacher);
        firstLesson.setTheme(theme);
        firstLesson.setLessonDateTime(Timestamp.from(Instant.now().plus(10, ChronoUnit.DAYS)));
        lessonDao.save(firstLesson);
        isEquals(lessonDao, firstLesson);

        Lesson secondLesson = new Lesson();
        secondLesson.setGroup(group);
        secondLesson.setTeacher(teacher);
        secondLesson.setTheme(theme);
        secondLesson.setLessonDateTime(Timestamp.from(Instant.now().plus(20, ChronoUnit.DAYS)));
        lessonDao.save(secondLesson);
        isEquals(lessonDao, secondLesson);

        Optional<Lesson> nearestLessonByStudentId = lessonService.findNearestLessonByStudentId(student.getId());
        assertTrue(nearestLessonByStudentId.isPresent());

        lessonService.remove(firstLesson);
        lessonService.remove(secondLesson);
        themeDao.delete(theme);
        teacherDao.delete(teacher);
        courseDao.delete(course);
    }

    private <T extends AbstractEntity> void isEquals(AbstractDao<T> abstractDao, T entity) {
        Optional<T> savedEntity = abstractDao.getById(entity.getId());
        assertTrue(savedEntity.isPresent() && entity.equals(savedEntity.get()));
    }

}
