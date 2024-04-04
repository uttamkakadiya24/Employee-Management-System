package com.training.ems.controller;

import com.training.ems.dto.*;
import com.training.ems.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final InventoryService inventoryService;
    private final StatisticsService statisticsService;
    private final LeaveRequestService leaveRequestService;
    private final WFHService wfhService;
    private final PeripheralService peripheralService;

    @PostMapping("/admin-signup")
    public ResponseEntity<AdminDto> registerAdmin(
            @RequestBody AdminDto adminDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.registerAdmin(adminDto));
    }

    @PreAuthorize("hasPermission('ADMIN','READ')")
    @GetMapping("/all-employee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        return ResponseEntity.ok().body(adminService.getAllEmployee());
    }

    @PreAuthorize("hasPermission('ADMIN','READ')")
    @GetMapping("/employee-id/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            String employeeId
    ) {
        return ResponseEntity.ok().body(adminService.getEmployeeById(employeeId));
    }

    @PreAuthorize("hasPermission('ADMIN','READ')")
    @GetMapping("/employee-stats")
    public ResponseEntity<List<StatsDto>> getEmployeeStats(){
        return ResponseEntity.ok().body(statisticsService.getStats());
    }

    @PreAuthorize("hasPermission('ADMIN','DELETE')")
    @DeleteMapping("/delete/employee-id/{id}")
    public HttpStatus deleteEmployee(
            @PathVariable String id
    ){
        adminService.deleteEmployee(id);
        return HttpStatus.OK;
    }

    @PreAuthorize("hasPermission('ADMIN','CREATE')")
    @PostMapping("/add-inventory")
    public ResponseEntity<InventoryDto> addItemToInventory(@RequestBody InventoryDto inventoryDto) {
        return ResponseEntity.ok().body(inventoryService.addItemToInventory(inventoryDto));
    }

    @PreAuthorize("hasPermission('ADMIN','CREATE')")
    @GetMapping("/request/purchase/approve/manager-id/{managerId}/request-id/{requestId}")
    public PurchaseDto approvePurchaseRequest(
            @PathVariable("managerId") String managerId,
            @PathVariable("requestId") String requestId
    ) {
        return peripheralService.approvePurchaseRequest(managerId,requestId );
    }

    @PreAuthorize("hasPermission('ADMIN','READ')")
    @GetMapping("/get-all-leave-request")
    public List<LeaveRequestDto> getAllLeaveRequests() {
        return leaveRequestService.getAllLeaveRequest();
    }

    @PreAuthorize("hasPermission('ADMIN','READ')")
    @GetMapping("/get-all-wfh-request")
    public List<WfhRequestDto> getAllWfhRequests() {
        return wfhService.getAllWfhRequest();
    }

}
