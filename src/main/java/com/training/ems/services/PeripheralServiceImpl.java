package com.training.ems.services;

import com.training.ems.dao.EmployeeRepository;
import com.training.ems.dao.InventoryRepository;
import com.training.ems.dao.PeripheralRepository;
import com.training.ems.dao.PurchaseRepository;
import com.training.ems.dto.PeripheralRequestDto;
import com.training.ems.dto.PurchaseDto;
import com.training.ems.entities.Employee;
import com.training.ems.entities.Inventory;
import com.training.ems.entities.PeripheralRequest;
import com.training.ems.entities.Purchase;
import com.training.ems.mapper.PeripheralRequestMapper;
import com.training.ems.mapper.PurchaseMapper;
import com.training.ems.securityService.AuthService;
import com.training.ems.util.enums.StatusType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PeripheralServiceImpl implements PeripheralService{

    private final PeripheralRepository peripheralRepository;
    private final InventoryRepository inventoryRepository;
    private final PurchaseRepository purchaseRepository;
    private final EmployeeRepository employeeRepository;
    private final InventoryService inventoryService;
    private final AuthService authService;

    @Override
    public PeripheralRequestDto createPeripheralRequest(PeripheralRequestDto peripheralRequestDto) {
        PeripheralRequest peripheralRequest = PeripheralRequestMapper.INSTANCE.toEntity(peripheralRequestDto);

        authService.checkValidLoggedUser(peripheralRequest.getEmployeeId());

        Integer quantityInInventory = inventoryService
            .checkInInventoryByInventoryType(peripheralRequest.getInventoryType())
                .map(Inventory::getQuantity)
                .orElse(0);

        if (quantityInInventory > 0) {
            peripheralRequest.setStatus(StatusType.PENDING);
            peripheralRepository.save(peripheralRequest);
            return PeripheralRequestMapper.INSTANCE.toDto(peripheralRequest);
        } else {
            Purchase purchase = new Purchase();
            purchase.setPeripheralRequest(peripheralRequest);
            purchase.setStatus(StatusType.PENDING);
            purchaseRepository.save(purchase);
        }

        return PeripheralRequestMapper.INSTANCE.toDto(peripheralRequest);
    }

    @Override
    public PeripheralRequestDto approvePeripheralRequest(String managerId,String employeeId,String requestId){

        PeripheralRequest peripheralRequest = peripheralRepository.findByIdOrThrow(requestId);

        authService.checkValidLoggedUser(peripheralRequest.getManagerId());

        if (peripheralRequest.getStatus().equals(StatusType.ALLOCATED)){
            throw  new IllegalArgumentException("Requested Peripheral is already Allocated to this "+employeeId);
        }

        if (peripheralRequest.getStatus() == StatusType.PENDING) {
            peripheralRequest.setStatus(StatusType.APPROVED);
            peripheralRepository.save(peripheralRequest);
            allocatePeripheralToEmployee(PeripheralRequestMapper.INSTANCE.toDto(peripheralRequest));
        }
        return PeripheralRequestMapper.INSTANCE.toDto(peripheralRequest);
    }

    @Override
    public PeripheralRequestDto rejectPeripheralRequest(String managerId, String employeeId, String requestId){
        PeripheralRequest peripheralRequest = peripheralRepository.findByIdOrThrow(requestId);

        authService.checkValidLoggedUser(peripheralRequest.getManagerId());

        if (peripheralRequest.getStatus().equals(StatusType.REJECTED)){
            throw  new IllegalArgumentException("Request is already REJECTED");
        }

        if (peripheralRequest.getStatus() == StatusType.PENDING) {
            peripheralRequest.setStatus(StatusType.REJECTED);
            peripheralRepository.save(peripheralRequest);
            allocatePeripheralToEmployee(PeripheralRequestMapper.INSTANCE.toDto(peripheralRequest));
        }
        return PeripheralRequestMapper.INSTANCE.toDto(peripheralRequest);

    }

    private void allocatePeripheralToEmployee(PeripheralRequestDto peripheralRequestDto) {

        PeripheralRequest peripheralRequest = PeripheralRequestMapper.INSTANCE.toEntity(peripheralRequestDto);
        if(peripheralRequest.getStatus() == StatusType.APPROVED) {
            peripheralRequest.setStatus(StatusType.ALLOCATED);
            peripheralRepository.save(peripheralRequest);

            inventoryService.updateQuantityInInventory(peripheralRequestDto);

            Employee employee = employeeRepository.findById(peripheralRequest.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException("Employee NOT Found"));
            List<PeripheralRequest> peripheralRequestList = employee.getPeripheralRequestList();

            if (peripheralRequestList == null ) {
                peripheralRequestList = new ArrayList<>();
                employee.setPeripheralRequestList(peripheralRequestList);
            }

            peripheralRequestList.add(peripheralRequest);

            employeeRepository.save(employee);
        }
    }

    @Override
    public PurchaseDto approvePurchaseRequest(String managerId, String requestId) {
        Purchase purchase = purchaseRepository.findByIdOrThrow(requestId);

        authService.checkValidLoggedUser(purchase.getAdminId());

        if (purchase.getStatus().equals(StatusType.APPROVED)){
            throw  new IllegalArgumentException("Request is already APPROVED");
        }

            purchase.setStatus(StatusType.APPROVED);
            purchaseRepository.save(purchase);
            updateInventory(purchase);

            return PurchaseMapper.INSTANCE.toDto(purchase);
    }

    @Override
    public PurchaseDto rejectPurchaseRequest(String managerId, String requestId) {
        Purchase purchase = purchaseRepository.findByIdOrThrow(requestId);

        authService.checkValidLoggedUser(purchase.getAdminId());

        if (purchase.getStatus().equals(StatusType.REJECTED)){
            throw  new IllegalArgumentException("Request is already REJECTED");
        }

        purchase.setStatus(StatusType.REJECTED);
        purchaseRepository.save(purchase);
        updateInventory(purchase);

        return PurchaseMapper.INSTANCE.toDto(purchase);
    }

    private void updateInventory(Purchase purchase) {
        Inventory inventory = inventoryRepository
                .findByInventoryType(purchase.getPeripheralRequest().getInventoryType());

        if (inventory != null) {
            inventory.setQuantity(inventory.getQuantity() + purchase.getPeripheralRequest().getQuantity());
            inventory.setInventoryType(inventory.getInventoryType());
            inventoryRepository.save(inventory);
        } else {
            Inventory inventory1 = new Inventory();
            inventory1.setInventoryType(purchase.getPeripheralRequest().getInventoryType());
            inventory1.setQuantity(purchase.getPeripheralRequest().getQuantity());
            inventoryRepository.save(inventory1);
        }
    }

}

