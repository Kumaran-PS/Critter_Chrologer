package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface employeeRepo extends JpaRepository<employee, Long> {
        List<employee> getAllByDaysAvailableContains(DayOfWeek dayOfWeek);
}
