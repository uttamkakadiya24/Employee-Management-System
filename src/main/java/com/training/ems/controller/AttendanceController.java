package com.training.ems.controller;

import com.training.ems.dto.AttendanceDto;
import com.training.ems.services.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private AttendanceService attendanceService;

    @PostMapping("/in/{employeeId}")
    public AttendanceDto employeeEntry(@PathVariable String employeeId){
        return attendanceService.employeeEntry(employeeId);
    }

    @PostMapping("/out/{employeeId}")
    public AttendanceDto employeeExit(@PathVariable String employeeId){
        return attendanceService.employeeExit(employeeId);
    }
}
