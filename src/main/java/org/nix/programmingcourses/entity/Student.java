package org.nix.programmingcourses.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends AbstractEntity {

    @Embedded
    PersonData personData;

    @ManyToOne
    @JoinColumn(
            name = "group_id",
            nullable = false
    )
    Group group;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Mark> marks = new ArrayList<>();

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        marks.forEach(this::addMark);
    }

    public void addMark(Mark mark) {
        mark.setStudent(this);
        marks.add(mark);
    }

    public void removeMark(Mark mark) {
        marks.remove(mark);
        mark.setStudent(null);
    }

}
