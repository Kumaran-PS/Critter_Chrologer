package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.Entity.customer;
import com.udacity.jdnd.course3.critter.Entity.employee;
import com.udacity.jdnd.course3.critter.Entity.pet;
import com.udacity.jdnd.course3.critter.Service.Customer_Service;
import com.udacity.jdnd.course3.critter.Service.Employee_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private Customer_Service customer_service;

    @Autowired
    private Employee_Service employee_service;

    public UserController(Customer_Service customer_service, Employee_Service employee_service) {
        this.customer_service = customer_service;
        this.employee_service = employee_service;
    }

    private EmployeeDTO getDTOEmployee(employee employee) {

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSkills(employee.getSkills());

        employeeDTO.setDaysAvailable(employee.getDaysAvailable());

        return employeeDTO;
    }
    
    private CustomerDTO getDTOCustomer(customer customer) {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());

        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNotes(customer.getNotes());

        List<Long> petIds = customer
                .getPets()
                .stream()
                .map(pet::getId)
                .collect(Collectors.toList());

        customerDTO.setPetIds(petIds);

        return customerDTO;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        customer customer = new customer();

        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());

        List<Long> petIds = customerDTO.getPetIds();
        CustomerDTO customerdto = new CustomerDTO();
        customerdto = getDTOCustomer(customer_service.save_Customer(customer, petIds));

        return customerdto;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

        List<customer> customers = customer_service.get_All_Customers();

        return customers
                .stream()
                .map(this::getDTOCustomer)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        CustomerDTO customerdto = new CustomerDTO();
        customerdto = getDTOCustomer(customer_service.get_Customer_By_PetId(petId));

        return customerdto;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        employee employees = new employee();

        employees.setName(employeeDTO.getName());
        employees.setSkills(employeeDTO.getSkills());
        employees.setDaysAvailable(employeeDTO.getDaysAvailable());

        EmployeeDTO employeedto = new EmployeeDTO();
        employeedto = getDTOEmployee(employee_service.saveEmployee(employees));

        return employeedto;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) throws Exception {

        EmployeeDTO employeedto = new EmployeeDTO();
        employeedto = getDTOEmployee(employee_service.get_Employee_By_Id(employeeId));

        return employeedto;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) throws Exception {

        employee_service.set_Employee_Availability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
//        (Set<EmployeeSkill> skill, LocalDate date)
        List<employee> employees = employee_service.get_Employees_For_Service(employeeDTO.getSkills(),employeeDTO.getDate());

        List<EmployeeDTO> result_availability = employees
                .stream()
                .map(this::getDTOEmployee)
                .collect(Collectors.toList());

        return result_availability;
    }

}
