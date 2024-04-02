package com.training.ems.services;

import com.training.ems.dto.PeripheralRequestDto;
import com.training.ems.dto.PurchaseDto;

public interface PeripheralService {

    PeripheralRequestDto createPeripheralRequest(PeripheralRequestDto peripheralRequestDto);
    PeripheralRequestDto approvePeripheralRequest(String managerId, String employeeId, String requestId);
    PeripheralRequestDto rejectPeripheralRequest(String managerId, String employeeId, String requestId);
    PurchaseDto approvePurchaseRequest(String managerId, String requestId);
    PurchaseDto rejectPurchaseRequest(String managerId, String requestId);

}
