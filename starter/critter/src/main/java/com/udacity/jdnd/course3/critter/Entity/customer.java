package com.udacity.jdnd.course3.critter.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table
@Entity
@Data
@NoArgsConstructor
public class customer implements Serializable {
    private String name;
    private String phoneNumber;
    private String notes;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void insertPet(pet pet) {
        pets.add(pet);
    }

    @OneToMany(targetEntity = pet.class)
    private List<pet> pets;

    public customer(String name, String phoneNumber, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
