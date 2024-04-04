package com.training.ems.services;

import com.training.ems.dao.AdminRepository;
import com.training.ems.dao.EmployeeRepository;
import com.training.ems.dto.AdminDto;
import com.training.ems.dto.EmployeeDto;
import com.training.ems.entities.Admin;
import com.training.ems.entities.Employee;
import com.training.ems.mapper.AdminMapper;
import com.training.ems.mapper.EmployeeMapper;
import com.training.ems.util.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminDto registerAdmin(AdminDto adminDto) {
        Admin admin = AdminMapper.INSTANCE.toEntity(adminDto);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(Role.ADMIN);
        return AdminMapper.INSTANCE.toDto(adminRepository.save(admin));
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {

        return EmployeeMapper.INSTANCE.toDto(employeeRepository.findAll());
    }

    @Override
    public EmployeeDto getEmployeeById(String employeeId) {
        Employee employee = employeeRepository.findByIdOrThrow(employeeId);
        return EmployeeMapper.INSTANCE.toDto(employee);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

}
