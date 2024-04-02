package com.training.ems.dao;

import com.training.ems.entities.Manager;
import com.training.ems.util.exception.EntityNotFoundByUsernameException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ManagerRepository extends MongoRepository<Manager , String> {

    Optional<Manager> findByUsername(String username);
    default Manager findByUsernameOrElseThrow(String username) {
        return findByUsername(username).orElseThrow(() -> new EntityNotFoundByUsernameException("Manager",username));
    }

}
