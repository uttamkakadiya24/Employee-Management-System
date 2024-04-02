package com.training.ems.dao;

import com.training.ems.entities.Purchase;
import com.training.ems.util.exception.EntityNotFoundByIdException;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepository extends MongoRepository<Purchase,String> {

    default Purchase findByIdOrThrow(String id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundByIdException("Purchase", id));
    }

}
