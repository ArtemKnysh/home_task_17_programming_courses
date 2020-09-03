package org.nix.programmingcourses.entity;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Mark extends AbstractEntity {

    @ManyToOne()
    @JoinColumn(
            name = "lesson_id",
            nullable = false
    )
    Lesson lesson;

    @ManyToOne()
    @JoinColumn(
            name = "student_id",
            nullable = false
    )
    Student student;

    @Column(name = "mark_value")
    Integer markValue;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getMarkValue() {
        return markValue;
    }

    public void setMarkValue(Integer markValue) {
        this.markValue = markValue;
    }
}
