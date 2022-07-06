package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.customer;
import com.udacity.jdnd.course3.critter.Entity.pet;
import com.udacity.jdnd.course3.critter.Repository.customerRepo;
import com.udacity.jdnd.course3.critter.Repository.petsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Customer_Service {
    @Autowired
    private customerRepo customerRepo;

    private petsRepo petsRepo;

    public Customer_Service(petsRepo petsRepo) {
        this.petsRepo = petsRepo;
    }

    public List<customer> get_All_Customers() {
        //finding list of customers from customer repository
        List<customer> cust = customerRepo.findAll();
        return cust;
    }

    public customer save_Customer(customer customer, List<Long> id_pet) {
        List<pet> petOfCustomers = new ArrayList<pet>();
        if (id_pet == null || id_pet.isEmpty()) {
        } else {
            // mapping customer with their pets based on the petIds using streams
            petOfCustomers = id_pet.stream()
                    .map((petId)
                            -> petsRepo.getOne(petId))
                    .collect(Collectors.toList());
        }
        customer.setPets(petOfCustomers);

        return customerRepo.save(customer);
    }

    public customer get_Customer_By_PetId(long id_pet) {
        // collecting customer from the pet id
        customer cust = petsRepo
                .getOne(id_pet)
                .getCustomer();
        return cust;
    }
}
