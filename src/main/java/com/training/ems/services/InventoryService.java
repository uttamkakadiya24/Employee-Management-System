package com.training.ems.services;

import com.training.ems.dto.InventoryDto;
import com.training.ems.dto.PeripheralRequestDto;
import com.training.ems.entities.Inventory;
import com.training.ems.util.enums.InventoryType;

import java.util.Optional;

public interface InventoryService {

    InventoryDto addItemToInventory(InventoryDto inventoryDto);
    Optional<Inventory> checkInInventoryByInventoryType(InventoryType inventoryType);
    void updateQuantityInInventory(PeripheralRequestDto peripheralRequestDto);
}
