package com.training.ems.services;

import com.training.ems.dao.AdminRepository;
import com.training.ems.dao.EmployeeRepository;
import com.training.ems.dto.AdminDto;
import com.training.ems.dto.EmployeeDto;
import com.training.ems.entities.Admin;
import com.training.ems.entities.Employee;
import com.training.ems.mapper.AdminMapper;
import com.training.ems.mapper.EmployeeMapper;
import com.training.ems.permissions.Role;
import com.training.ems.securityService.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public AdminDto registerAdmin(AdminDto adminDto) {
        Admin admin = AdminMapper.INSTANCE.toEntity(adminDto);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(Role.ADMIN);
        adminRepository.save(admin);
        return AdminMapper.INSTANCE.toDto(admin);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {

        return EmployeeMapper.INSTANCE.toDto(employeeRepository.findAll());
    }

    @Override
    public EmployeeDto getEmployeeById(EmployeeDto employeeDto) {

        Employee employee = employeeRepository.findByIdOrThrow(authService.getLoggedInUserId());
        return EmployeeMapper.INSTANCE.toDto(employee);
    }

    @Override
    public void  deleteEmployee(String employeeId) {

        String loggedAdminId = authService.getLoggedInUserId();

        if (!(employeeId.equals(loggedAdminId))){
            throw new RuntimeException("delete only by loggedIn ADMIN");
        }
        employeeRepository.deleteById(employeeId);

    }

}
