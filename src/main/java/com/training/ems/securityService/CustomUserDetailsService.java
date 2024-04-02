package com.training.ems.securityService;

import com.training.ems.dao.AdminRepository;
import com.training.ems.dao.EmployeeRepository;
import com.training.ems.dao.ManagerRepository;
import com.training.ems.util.exception.EntityNotFoundByUsernameException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private final ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user;

        try{
            user = employeeRepository.findByUsernameOrElseThrow(username);
        } catch (EntityNotFoundByUsernameException ex) {
            try {
                user = adminRepository.findByUsernameOrThrow(username);
            } catch (EntityNotFoundByUsernameException ex1) {
                try {
                    user = managerRepository.findByUsernameOrElseThrow(username);
                } catch (EntityNotFoundByUsernameException ex2) {
                    throw new RuntimeException("User Not Found!!");
                }
            }
        }

        return user;
    }

}
