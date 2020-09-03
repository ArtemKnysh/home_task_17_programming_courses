package org.nix.programmingcourses.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher extends AbstractEntity {

    @Embedded
    PersonData personData;

    @OneToMany(
            mappedBy = "teacher",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Lesson> lessons;

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        lessons.forEach(this::addLesson);
    }

    public void addLesson(Lesson lesson) {
        lesson.setTeacher(this);
        lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
        lesson.setTeacher(null);
    }

}
