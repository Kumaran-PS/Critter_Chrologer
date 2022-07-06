package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entity.pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface petsRepo extends JpaRepository<pet, Long> {

    List<pet> getAllByCustomerId(Long customerId);
}
