package org.nix.programmingcourses.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course extends AbstractEntity {

    @Column(nullable = false)
    String name;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Group> groups = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        groups.forEach(this::addGroup);
    }

    public void addGroup(Group group) {
        group.setCourse(this);
        groups.add(group);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
        group.setCourse(null);
    }

}
