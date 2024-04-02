package com.training.ems.dto;

import com.training.ems.entities.PeripheralRequest;
import com.training.ems.util.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor
public class PurchaseDto {

    public String adminId;
    public String employeeId;
    public PeripheralRequest peripheralRequest;
    public StatusType status;
}
