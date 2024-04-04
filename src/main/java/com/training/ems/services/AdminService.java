package com.training.ems.services;

import com.training.ems.dto.AdminDto;
import com.training.ems.dto.EmployeeDto;

import java.util.List;


public interface AdminService {

    AdminDto registerAdmin(AdminDto adminDto);
    List<EmployeeDto> getAllEmployee();
    EmployeeDto getEmployeeById(String employeeId);

     void deleteEmployee(String employeeId);


}
