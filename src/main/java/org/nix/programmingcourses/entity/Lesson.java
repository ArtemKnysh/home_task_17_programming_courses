package org.nix.programmingcourses.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
public class Lesson extends AbstractEntity {

    @ManyToOne
    @JoinColumn(
            name = "group_id",
            nullable = false
    )
    Group group;

    @ManyToOne
    @JoinColumn(
            name = "theme_id",
            nullable = false
    )
    Theme theme;

    @OneToMany(
            mappedBy = "lesson",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Mark> marks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(
            name = "teacher_id",
            nullable = false
    )
    Teacher teacher;

    @Column(
            name="lesson_date_time",
            nullable = false
    )
    Timestamp lessonDateTime;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Timestamp getLessonDateTime() {
        return lessonDateTime;
    }

    public void setLessonDateTime(Timestamp lessonDateAndTime) {
        this.lessonDateTime = lessonDateAndTime;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void addMark(Mark mark) {
        mark.setLesson(this);
        marks.add(mark);
    }

    public void removeMark(Mark mark) {
        marks.remove(mark);
        mark.setLesson(null);
    }



}
