package com.training.ems.controller;

import com.training.ems.dto.*;
import com.training.ems.services.LeaveRequestService;
import com.training.ems.services.ManagerService;
import com.training.ems.services.PeripheralService;
import com.training.ems.services.WFHService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final LeaveRequestService leaveRequestService;
    private final WFHService wfhService;
    private final PeripheralService peripheralService;
    private final ManagerService managerService;

    @PostMapping("/manager-signup")
    public ResponseEntity<ManagerDto> registerManager(@RequestBody ManagerDto managerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(managerService.registerManager(managerDto));
    }

    @GetMapping("/request/leave/approve/manager-id/{managerId}/request-id/{requestId}")
    public LeaveRequestDto approveLeaveRequest(@PathVariable("managerId") String managerId,
                                               @PathVariable("requestId") String requestId){
        return leaveRequestService.approveLeaveRequest(managerId,requestId);
    }

    @GetMapping("/request/wfh/approve/manager-id/{managerId}/request-id/{requestId}")
    public WfhRequestDto approveWfhRequest(@PathVariable("managerId") String managerId,
                                           @PathVariable("requestId") String requestId){
        return wfhService.approveWfhRequest(managerId,requestId);
    }

    @GetMapping("/request/peripheral/approve/manager-id/{managerId}/employee-id/{employeeId}/request-id/{requestId}")
    public PeripheralRequestDto approvePeripheralRequest(@PathVariable("managerId") String managerId,
                                                         @PathVariable("employeeId") String employeeId,
                                                         @PathVariable("requestId") String requestId){
        return peripheralService.approvePeripheralRequest(managerId,employeeId,requestId );
    }

    @GetMapping("/request/peripheral/reject/manager-id/{managerId}/employee-id/{employeeId}/request-id/{requestId}")
    public PeripheralRequestDto rejectPeripheralRequest(@PathVariable("managerId") String managerId,
                                                         @PathVariable("employeeId") String employeeId,
                                                         @PathVariable("requestId") String requestId){
        return peripheralService.rejectPeripheralRequest(managerId,employeeId,requestId );
    }

    @GetMapping("/request/purchase/approve/manager-id/{managerId}/request-id/{requestId}")
    public PurchaseDto approvePurchaseRequest(@PathVariable("managerId") String managerId,
                                              @PathVariable("requestId") String requestId){
        return peripheralService.approvePurchaseRequest(managerId,requestId );
    }

    @GetMapping("/request/purchase/reject/manager-id/{managerId}/request-id/{requestId}")
    public PurchaseDto rejectPurchaseRequest(@PathVariable("managerId") String managerId,
                                              @PathVariable("requestId") String requestId){
        return peripheralService.rejectPurchaseRequest(managerId,requestId );
    }

    @GetMapping("/request/leave/reject/manager-id/{managerId}/request-id/{requestId}")
    public LeaveRequestDto rejectLeaveRequest(@PathVariable("managerId") String managerId,
                                           @PathVariable("requestId") String requestId){
        return leaveRequestService.rejectLeaveRequest(managerId,requestId);
    }

    @GetMapping("/request/wfh/reject/manager-id/{managerId}/request-id/{requestId}")
    public WfhRequestDto rejectWfhRequest(@PathVariable("managerId") String managerId,
                                       @PathVariable("requestId") String requestId){
        return wfhService.rejectWfhRequest(managerId,requestId);
    }
}
