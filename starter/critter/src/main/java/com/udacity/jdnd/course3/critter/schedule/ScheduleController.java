package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.Entity.employee;
import com.udacity.jdnd.course3.critter.Entity.pet;
import com.udacity.jdnd.course3.critter.Entity.schedule;
import com.udacity.jdnd.course3.critter.Service.Schedule_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private Schedule_Service schedule_service;

    public ScheduleController(Schedule_Service schedule_service) {
        this.schedule_service = schedule_service;
    }

    private ScheduleDTO getDTOSchedule(schedule schedule) {

        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setEmployeeIds(schedule
                .getEmployees()
                .stream().map(employee::getId)
                .collect(Collectors.toList()));

        scheduleDTO.setPetIds(schedule
                .getPets()
                .stream().map(pet::getId)
                .collect(Collectors.toList()));

        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setActivities(schedule.getActivities());

        return scheduleDTO;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) throws Exception {
        schedule schedules = new schedule();

        schedules.setDate(scheduleDTO.getDate());
        schedules.setActivities(scheduleDTO.getActivities());

//        (List<Long> employeeIds,List<Long> idOfPet,schedule schedule)
        ScheduleDTO scheduledto = new ScheduleDTO();
        scheduledto = getDTOSchedule(schedule_service.save_Schedule(scheduleDTO
                        .getEmployeeIds(),
                scheduleDTO.getPetIds() ,
                schedules));

        return scheduledto;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<schedule> schedules = schedule_service.get_All_Schedules();

        List<ScheduleDTO> result_schedules = schedules

                .stream()
                .map(this::getDTOSchedule)
                .collect(Collectors.toList());

        return result_schedules;

    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<schedule> schedules = schedule_service.get_All_Schedules_ForPet(petId);

        List<ScheduleDTO> result_schedules = schedules

                .stream()
                .map(this::getDTOSchedule)
                .collect(Collectors.toList());

        return result_schedules;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<schedule> schedules = schedule_service.get_All_Schedules_For_Employee(employeeId);

        List<ScheduleDTO> result_schedules = schedules

                .stream()
                .map(this::getDTOSchedule)
                .collect(Collectors.toList());

        return result_schedules;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<schedule> schedules = schedule_service.get_All_Schedules_For_Customer(customerId);

        List<ScheduleDTO> result_schedules = schedules

                .stream()
                .map(this::getDTOSchedule)
                .collect(Collectors.toList());

        return result_schedules;
    }
}
