package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.customer;
import com.udacity.jdnd.course3.critter.Entity.pet;
import com.udacity.jdnd.course3.critter.Repository.customerRepo;
import com.udacity.jdnd.course3.critter.Repository.petsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pet_Service {
    @Autowired
    private petsRepo petsRepo;

    @Autowired
    private customerRepo customersRepo;

    public Pet_Service(petsRepo petsRepo, customerRepo customersRepo) {
        this.petsRepo = petsRepo;
        this.customersRepo = customersRepo;
    }


    public pet get_Pet_By_Id(long petId) throws Exception {
        try{
            // collecting pet from pet id
            pet petOfCustomers = petsRepo.getOne(petId);
            return  petOfCustomers;
        }catch (Exception e){
            throw new Exception("Pet with ID "+petId+ " not found"+ e);
        }
    }


    public List<pet> get_All_Pets() {
        // finding list of all pets in database
        List<pet> petsOfCustomers = petsRepo.findAll();
        return petsOfCustomers;
    }


    public List<pet> get_Pets_By_CustomerId(long id_employee) throws Exception {
        try{
            // collecting list of pets from the customer id
            List<pet> petsOfCustomers =petsRepo.getAllByCustomerId(id_employee);
            return petsOfCustomers;
        }catch (Exception e){
            throw new Exception("Employee with ID "+id_employee+ " not found"+ e);
        }

    }
    public pet save_Pet(pet petsOfCustomers, long petOwnerID)  {

        // mapping pets and their cusotmers (OWNER ID)
            customer customer = customersRepo.getOne(petOwnerID);
            petsOfCustomers.setCustomer(customer);
//            petsOfCustomers = petsRepo.save(petsOfCustomers);
            customer.insertPet(petsRepo.save(petsOfCustomers));
            customersRepo.save(customer);
            return petsOfCustomers;

    }

}
