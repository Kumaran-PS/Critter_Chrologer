package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entity.employee;
import com.udacity.jdnd.course3.critter.Entity.pet;
import com.udacity.jdnd.course3.critter.Entity.schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface scheduleRepo extends JpaRepository<schedule, Long> {
    List<schedule> getAllByPetsIn(List<pet> pets);
    List<schedule> getAllByPetsContains(pet pet);
    List<schedule> getAllByEmployeesContains(employee employee);
//    getAllByPetsIn[](List<pet> pets);
//    List<schedule> getAllByPetsIn(List<pet> pets);
}

