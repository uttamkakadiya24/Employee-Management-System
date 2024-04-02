package com.training.ems.validation;

import com.training.ems.entities.Employee;
import com.training.ems.entities.Manager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class Validation {


    public String getLoggedEmployeeId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user = authentication.getPrincipal();

        if (user instanceof Employee employee) {
            return employee.getId();
        }
        throw new RuntimeException("User is not instance of Employee");
    }

    public String getLoggedManagerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user = authentication.getPrincipal();

        if(user instanceof Manager manager) {
            return manager.getId();
        }
        throw new RuntimeException("User is not instance of Manager");
    }

}
