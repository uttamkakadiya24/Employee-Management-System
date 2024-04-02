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

    @PreAuthorize("hasPermission('Employee','CREATE')")
    @PostMapping("/employee-signup")
    public ResponseEntity<EmployeeDto> registerEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.registerEmployee(employeeDto));
    }

    @PutMapping("/update-employee/employee-username/{username}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestParam String username, @RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok().body(employeeService.updateEmployee(username,employeeDto));
    }

    @PostMapping("/create-leave-request")
    public ResponseEntity<LeaveRequestDto> createLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto){
        return ResponseEntity.ok().body(leaveRequestService.createLeaveRequest(leaveRequestDto));
    }

    @PostMapping("/create-wfh-request")
    public ResponseEntity<WfhRequestDto> createWFHRequest(@RequestBody WfhRequestDto wfhRequestDto){
        return ResponseEntity.ok().body(wfhService.createWFHRequest(wfhRequestDto));
    }

    @PostMapping("/create-peripheral-request")
    public ResponseEntity<PeripheralRequestDto> createPeripheralRequest(
            @RequestBody PeripheralRequestDto peripheralRequestDto){
        return ResponseEntity.ok().body(peripheralService.createPeripheralRequest(peripheralRequestDto));
    }

}
