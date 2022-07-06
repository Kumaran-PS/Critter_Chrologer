package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Table
@Entity
@Data
@NoArgsConstructor
public class pet implements Serializable {
    private PetType type;
    private String name;
    private LocalDate birthDate;
    private String notes;
    @ManyToOne(targetEntity = customer.class, optional = false)
    private customer customer;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public pet(PetType type, String name, LocalDate birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public pet(com.udacity.jdnd.course3.critter.Entity.customer customer) {
        this.customer = customer;
    }

    public com.udacity.jdnd.course3.critter.Entity.customer getCustomer() {
        return customer;
    }

    public void setCustomer(com.udacity.jdnd.course3.critter.Entity.customer customer) {
        this.customer = customer;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



}
