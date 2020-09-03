package org.nix.programmingcourses.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="groups")
public class Group extends AbstractEntity {
    @Column(nullable = false)
    String name;

    @Column(name = "start_eduction", nullable = false)
    LocalDate startEduction;

    @Column(name = "end_eduction", nullable = false)
    LocalDate endEduction;

    @ManyToOne
    @JoinColumn(
            name = "course_id",
            nullable = false
    )
    Course course;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Student> students = new ArrayList<>();

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Lesson> lessons = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartEduction() {
        return startEduction;
    }

    public void setStartEduction(LocalDate startEduction) {
        this.startEduction = startEduction;
    }

    public LocalDate getEndEduction() {
        return endEduction;
    }

    public void setEndEduction(LocalDate endEduction) {
        this.endEduction = endEduction;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        students.forEach(this::addStudent);
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        lessons.forEach(this::addLesson);
    }

    public void addStudent(Student student) {
        student.setGroup(this);
        students.add(student);
    }

    public void addLesson(Lesson lesson) {
        lesson.setGroup(this);
        lessons.add(lesson);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setGroup(null);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
        lesson.setGroup(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return name.equals(group.name) &&
                startEduction.equals(group.startEduction) &&
                endEduction.equals(group.endEduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startEduction, endEduction);
    }

}
