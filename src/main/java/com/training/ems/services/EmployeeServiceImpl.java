package com.training.ems.services;

import com.training.ems.dao.EmployeeRepository;
import com.training.ems.dto.EmployeeDto;
import com.training.ems.entities.Employee;
import com.training.ems.mapper.EmployeeMapper;
import com.training.ems.permissions.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeDto registerEmployee(EmployeeDto employeeDto){

        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDto);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole(Role.EMPLOYEE);
        return EmployeeMapper.INSTANCE.toDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto updateEmployee(String username,EmployeeDto employeeDto) {

        Employee employee = employeeRepository.findByUsernameOrElseThrow(username);

        EmployeeMapper.INSTANCE.updateEntity(employeeDto, employee);

//        employee.setName(employeeDto.getName());
//        employee.setEmail(employeeDto.getEmail());
//        employee.setAddress(employeeDto.getAddress());

        employeeRepository.save(employee);
        return EmployeeMapper.INSTANCE.toDto(employee);
    }

}
