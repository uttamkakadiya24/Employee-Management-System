package com.training.ems.dao;

import com.training.ems.entities.PeripheralRequest;
import com.training.ems.util.exception.EntityNotFoundByIdException;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeripheralRepository extends MongoRepository<PeripheralRequest,String> {
    default PeripheralRequest findByIdOrThrow(String id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundByIdException("PeripheralRequest", id));
    }
}
