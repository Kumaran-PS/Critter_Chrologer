package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entity.customer;
import com.udacity.jdnd.course3.critter.Entity.pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepo  extends JpaRepository<customer, Long> {
    customer findCustomerByPets(pet pet);
}

