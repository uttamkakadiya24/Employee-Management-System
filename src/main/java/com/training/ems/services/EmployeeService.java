package com.training.ems.services;

import com.training.ems.dto.EmployeeDto;

public interface EmployeeService {

//    EmployeeDto createEmployee(EmployeeDto employeeDto );
    EmployeeDto updateEmployee(EmployeeDto employeeDto);
    EmployeeDto registerEmployee(EmployeeDto employeeDto);

}
