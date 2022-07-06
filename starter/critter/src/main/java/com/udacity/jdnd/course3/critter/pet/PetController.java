package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Entity.pet;
import com.udacity.jdnd.course3.critter.Service.Pet_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    Pet_Service pet_service;

    public PetController(Pet_Service pet_service) {
        this.pet_service = pet_service;
    }

    private PetDTO getDTOPet(pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setType(pet.getType());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());
        return petDTO;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO)  {

        pet pet = new pet();

        pet.setType(petDTO.getType());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());

        PetDTO petdto = new PetDTO();
        petdto = getDTOPet(pet_service.save_Pet(pet, petDTO.getOwnerId()));

        return petdto;
    }


    @GetMapping
    public List<PetDTO> getPets(){

        List<pet> pet = pet_service.get_All_Pets();

        List<PetDTO> result_pet = pet
                .stream()
                .map(this::getDTOPet)
                .collect(Collectors.toList());

        return result_pet;

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) throws Exception {

         List<pet> pet = pet_service.get_Pets_By_CustomerId(ownerId);

         List<PetDTO> result_pet = pet
                 .stream()
                 .map(this::getDTOPet)
                 .collect(Collectors.toList());

         return result_pet;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) throws Exception {

        PetDTO petdto = new PetDTO();

        petdto = getDTOPet(pet_service.get_Pet_By_Id(petId));

        return petdto;
    }
}
