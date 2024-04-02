package com.training.ems.dao;

import com.training.ems.entities.Employee;
import com.training.ems.util.exception.EntityNotFoundByIdException;
import com.training.ems.util.exception.EntityNotFoundByUsernameException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository <Employee,String> {

    default Employee findByIdOrThrow(String id){
        return findById(id).orElseThrow(() -> new EntityNotFoundByIdException("Employee",id));
    }
    default Employee findByUsernameOrElseThrow(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundByUsernameException("Employee",username));
    }
    Optional<Employee> findByUsername(String email);

}