package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.employee;
import com.udacity.jdnd.course3.critter.Repository.employeeRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Employee_Service {

    @Autowired
    private employeeRepo employeeRepo;

    public Employee_Service(employeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    public employee saveEmployee(employee save_employee) {
        return employeeRepo.save(save_employee);
    }

    public employee get_Employee_By_Id(long id_employee) throws Exception {
        try{
            // collecting employee from the employee id
            employee emp = employeeRepo.getOne(id_employee);
            return emp;
        }
        catch(Exception e){
            throw new Exception("Employee with ID "+id_employee+ " not found"+ e);
        }
    }

    public void set_Employee_Availability(Set<DayOfWeek> daysLeft, long id_employee) throws Exception {
        try{
            // employee avilability on days of the week
            employee emp = employeeRepo.getOne(id_employee);
            emp.setDaysAvailable(daysLeft);
            employeeRepo.save(emp);
        }
        catch(Exception e){
            throw new Exception("Employee with ID "+id_employee+ " not found"+ e);
        }
    }

    public List<employee> get_Employees_For_Service(Set<EmployeeSkill> skill,LocalDate date) {
        List<employee> emp = employeeRepo
                // finding avilable emplyees for sevice of pets based on avialable dates - using stream
                .getAllByDaysAvailableContains(date.getDayOfWeek()).stream()
                .filter(employee -> employee.getSkills().containsAll(skill))
                .collect(Collectors.toList());
        return emp;
    }

    public List<employee> list(){
        List<employee> emp_list = employeeRepo.findAll();
        return emp_list;
    }
}
