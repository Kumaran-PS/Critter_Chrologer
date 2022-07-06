package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Table
@Entity
@Data
@NoArgsConstructor
public class employee {
    private String name;
    @ElementCollection
    private Set<EmployeeSkill> skills;
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public employee(String name ) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
