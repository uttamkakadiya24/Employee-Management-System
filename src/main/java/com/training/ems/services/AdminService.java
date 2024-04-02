package com.training.ems.services;

import com.training.ems.dto.AdminDto;
import com.training.ems.dto.EmployeeDto;

import java.util.List;


public interface AdminService {

    AdminDto registerAdmin(AdminDto adminDto);
    List<EmployeeDto> getAllEmployee();
    EmployeeDto getEmployeeById(EmployeeDto employeeDto);

     void deleteEmployee(String employeeId);
//    List<LeaveRequest> getLeaveRequest(String adminId);
//    List<WfhRequest> getWorkFromHomeRequest(String adminId);

}
