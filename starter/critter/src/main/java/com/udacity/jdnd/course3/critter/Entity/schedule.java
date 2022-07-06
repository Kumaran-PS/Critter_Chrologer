package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Data
@NoArgsConstructor
public class schedule {

    @ManyToMany(targetEntity = employee.class)
    private List<employee> employees;
    @ElementCollection
    private Set<EmployeeSkill> activities;
    private LocalDate date;
    @ManyToMany(targetEntity = pet.class)
    private List<pet> pets;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public schedule(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }




}
