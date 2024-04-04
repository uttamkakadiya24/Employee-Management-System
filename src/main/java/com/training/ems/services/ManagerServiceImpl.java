package com.training.ems.services;

import com.training.ems.dao.ManagerRepository;
import com.training.ems.dto.ManagerDto;
import com.training.ems.entities.Manager;
import com.training.ems.mapper.ManagerMapper;
import com.training.ems.util.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService{

    private final PasswordEncoder passwordEncoder;
    private final ManagerRepository managerRepository;

    public ManagerDto registerManager(ManagerDto managerDto) {
        Manager manager = ManagerMapper.INSTANCE.toEntity(managerDto);
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        manager.setRole(Role.MANAGER);
        return ManagerMapper.INSTANCE.toDto(managerRepository.save(manager));
    }

}
