package com.training.ems.services;

import com.training.ems.dao.InventoryRepository;
import com.training.ems.dto.InventoryDto;
import com.training.ems.dto.PeripheralRequestDto;
import com.training.ems.entities.Inventory;
import com.training.ems.entities.PeripheralRequest;
import com.training.ems.mapper.InventoryMapper;
import com.training.ems.mapper.PeripheralRequestMapper;
import com.training.ems.securityService.AuthService;
import com.training.ems.util.enums.InventoryType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final AuthService authService;

    @Override
    public InventoryDto addItemToInventory(InventoryDto inventoryDto) {

        String loggedAdminId = authService.getLoggedInUserId();

        Inventory inventory = InventoryMapper.INSTANCE.toEntity(inventoryDto);
        Optional<Inventory> checkInInventory = checkInInventoryByInventoryType(inventory.getInventoryType());

        if (!(inventory.getManagerId().equals(loggedAdminId))){
            throw new RuntimeException("Only the manager who created the inventory request can approve it");
        }

        if (checkInInventory.isPresent()){
            Inventory existingInventory = checkInInventory.get();
            existingInventory.setQuantity(existingInventory.getQuantity() + inventory.getQuantity());

            return InventoryMapper.INSTANCE.toDto( inventoryRepository.save(existingInventory));
        } else {
            Inventory newInventory = new Inventory();
            newInventory.setInventoryType(inventoryDto.getInventoryType());
            newInventory.setQuantity(inventoryDto.getQuantity());
            inventoryRepository.save(newInventory);
            return InventoryMapper.INSTANCE.toDto(newInventory);
        }

}

    @Override
    public Optional<Inventory> checkInInventoryByInventoryType (InventoryType inventoryType) {
        return Optional.ofNullable(inventoryRepository.findByInventoryType(inventoryType));
    }

    @Override
    public void updateQuantityInInventory(PeripheralRequestDto peripheralRequestDto) {

        PeripheralRequest peripheralRequest = PeripheralRequestMapper.INSTANCE.toEntity(peripheralRequestDto);
        Optional<Inventory> inventoryItem = checkInInventoryByInventoryType(peripheralRequest.getInventoryType());
        if (inventoryItem.isPresent() && inventoryItem.get().getQuantity() >= peripheralRequestDto.getQuantity()) {
            inventoryItem.get()
                    .setQuantity(inventoryItem.get().getQuantity() - peripheralRequestDto.getQuantity());
            inventoryRepository.save(inventoryItem.get());
        }
        PeripheralRequestMapper.INSTANCE.toDto(peripheralRequest);
    }
}
