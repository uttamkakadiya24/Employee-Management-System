package com.training.ems.controller;

import com.training.ems.dto.EmployeeDto;
import com.training.ems.dto.LeaveRequestDto;
import com.training.ems.dto.PeripheralRequestDto;
import com.training.ems.dto.WfhRequestDto;
import com.training.ems.services.EmployeeService;
import com.training.ems.services.LeaveRequestService;
import com.training.ems.services.PeripheralService;
import com.training.ems.services.WFHService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
//@PreAuthorize("hasRole('EMPLOYEE')")
public class EmployeeController {

    private EmployeeService employeeService;
    private LeaveRequestService leaveRequestService;
    private WFHService wfhService;
    private PeripheralService peripheralService;


    @PostMapping("/employee-signup")
    public ResponseEntity<EmployeeDto> registerEmployee(
            @RequestBody EmployeeDto employeeDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.registerEmployee(employeeDto));
    }

    @PreAuthorize("hasPermission('EMPLOYEE','UPDATE')")
    @PutMapping("/update-employee")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @RequestBody EmployeeDto employeeDto
    ) {
        return ResponseEntity.ok().body(employeeService.updateEmployee(employeeDto));
    }

    @PreAuthorize("hasPermission('EMPLOYEE','CREATE')")
    @PostMapping("/create-leave-request")
    public ResponseEntity<LeaveRequestDto> createLeaveRequest(
            @RequestBody LeaveRequestDto leaveRequestDto
    ) {
        return ResponseEntity.ok().body(leaveRequestService.createLeaveRequest(leaveRequestDto));
    }

    @PreAuthorize("hasPermission('EMPLOYEE','CREATE')")
    @PostMapping("/create-wfh-request")
    public ResponseEntity<WfhRequestDto> createWFHRequest(
            @RequestBody WfhRequestDto wfhRequestDto
    ) {
        return ResponseEntity.ok().body(wfhService.createWFHRequest(wfhRequestDto));
    }

    @PreAuthorize("hasPermission('EMPLOYEE','CREATE')")
    @PostMapping("/create-peripheral-request")
    public ResponseEntity<PeripheralRequestDto> createPeripheralRequest(
            @RequestBody PeripheralRequestDto peripheralRequestDto
    ) {
        return ResponseEntity.ok().body(peripheralService.createPeripheralRequest(peripheralRequestDto));
    }

}
