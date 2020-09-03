package org.nix.programmingcourses.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "themes")
public class Theme extends AbstractEntity {

    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "theme")
    List<Lesson> lessons = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        lessons.forEach(this::addLesson);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.setTheme(this);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
        lesson.setTheme(null);
    }

}
