package com.training.ems.dao;

import com.training.ems.entities.Inventory;
import com.training.ems.util.enums.InventoryType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory,String> {
    Inventory findByInventoryType(InventoryType type);
}
