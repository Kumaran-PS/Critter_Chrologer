package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.customer;
import com.udacity.jdnd.course3.critter.Entity.employee;
import com.udacity.jdnd.course3.critter.Entity.pet;
import com.udacity.jdnd.course3.critter.Entity.schedule;
import com.udacity.jdnd.course3.critter.Repository.customerRepo;
import com.udacity.jdnd.course3.critter.Repository.employeeRepo;
import com.udacity.jdnd.course3.critter.Repository.petsRepo;
import com.udacity.jdnd.course3.critter.Repository.scheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Schedule_Service {
    @Autowired
    private customerRepo customersRepo;

    @Autowired
    private employeeRepo employeesRepo;

    @Autowired
    private scheduleRepo schedulesRepo;

    @Autowired
    private petsRepo petsRepo;

    public Schedule_Service(customerRepo customersRepo,
                            employeeRepo employeesRepo,
                            scheduleRepo schedulesRepo,
                            petsRepo petsRepo) {
        this.customersRepo = customersRepo;
        this.employeesRepo = employeesRepo;
        this.schedulesRepo = schedulesRepo;
        this.petsRepo = petsRepo;
    }

    public List<schedule> get_All_Schedules_ForPet(long petId) {
        List<schedule> list_schedule = new ArrayList<>();
        try{
            // pets will be taken care on the available schedules
            // collecting pets from thier ids - from schedule repository
            pet petOfCustomer = petsRepo.getOne(petId);
            list_schedule = schedulesRepo.getAllByPetsContains(petOfCustomer);
            return list_schedule;
        }catch(Exception e){
            e.getLocalizedMessage();
        }
        return list_schedule;
    }
    public List<schedule> get_All_Schedules() {

        // collecting all schedules for servicing pets
        List<schedule> list_Schedule = new ArrayList<>();
        try{
            list_Schedule = schedulesRepo.findAll();
            return list_Schedule;
        }catch(Exception e){
           e.getLocalizedMessage();
        }
        return list_Schedule;
    }
    public schedule save_Schedule(List<Long> employeeIds,List<Long> idOfPet,schedule schedule) throws Exception {
        try{
//            saving schedules fro emplyes with inputs - pet ids , schedules , employee ids
            List<employee> employees = employeesRepo.findAllById(employeeIds);
            List<pet> pets = petsRepo.findAllById(idOfPet);
            schedule.setEmployees(employees);
            schedule.setPets(pets);
            return schedulesRepo.save(schedule);
        }catch(Exception e){
            throw new Exception("Employee with ID "+employeeIds+ " not found"+ e);
        }

    }

    public List<schedule> get_All_Schedules_For_Employee(long employeeId) {
        // collecting all schedules for a specific empluee from the employee ids
        employee employee = employeesRepo.getOne(employeeId);
        List<schedule>  list_schedule = schedulesRepo.getAllByEmployeesContains(employee);
        return list_schedule;
    }

    public List<schedule> get_All_Schedules_For_Customer(long customerId) {
        // collecting all schedules for a specific customer from the customer ids
        customer customer = customersRepo.getOne(customerId);
        List<schedule> list_schedule = schedulesRepo.getAllByPetsIn(customer.getPets());;
        return  list_schedule;
    }




}
