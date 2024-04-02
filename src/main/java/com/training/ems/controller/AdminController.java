package com.training.ems.controller;

import com.training.ems.dto.*;
import com.training.ems.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AdminDto> registerAdmin(@RequestBody AdminDto adminDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.registerAdmin(adminDto));
    }

    @GetMapping("/all-employee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        return ResponseEntity.ok().body(adminService.getAllEmployee());
    }

    @GetMapping("/employee-id/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok().body(adminService.getEmployeeById(employeeDto));
    }

    @GetMapping("/employee-stats")
    public ResponseEntity<List<StatsDto>> getEmployeeStats(){
        return ResponseEntity.ok().body(statisticsService.getStats());
    }

    @DeleteMapping("/delete/employee-id/{id}")
    public HttpStatus deleteEmployee(@PathVariable String id){
        adminService.deleteEmployee(id);
        return HttpStatus.OK;
    }

    @PostMapping("/add-inventory")
    public ResponseEntity<InventoryDto> addItemToInventory(@RequestBody InventoryDto inventoryDto) {
        return ResponseEntity.ok().body(inventoryService.addItemToInventory(inventoryDto));
    }

    @GetMapping("/request/purchase/approve/manager-id/{managerId}/request-id/{requestId}")
    public PurchaseDto approvePurchaseRequest(@PathVariable("managerId") String managerId,
                                              @PathVariable("requestId") String requestId) {
        return peripheralService.approvePurchaseRequest(managerId,requestId );
    }

    @GetMapping("/get-all-leave-request")
    public List<LeaveRequestDto> getAllLeaveRequests() {
        return leaveRequestService.getAllLeaveRequest();
    }

    @GetMapping("/get-all-wfh-request")
    public List<WfhRequestDto> getAllWfhRequests() {
        return wfhService.getAllWfhRequest();
    }
//
//    @GetMapping("/request/wfh/admin-id/{id}")
//    public List<WfhRequest> getWorkFromHomeRequestByAdminId(@PathVariable("id") String adminId){
//        return adminService.getWorkFromHomeRequest(adminId);
//    }


}
