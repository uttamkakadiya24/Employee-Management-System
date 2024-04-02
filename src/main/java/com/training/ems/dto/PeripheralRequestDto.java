package com.training.ems.dto;

import com.training.ems.util.enums.InventoryType;
import com.training.ems.util.enums.RequestType;
import com.training.ems.util.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeripheralRequestDto {

    private String managerId;
    private String employeeId;
    private InventoryType inventoryType;
    private Integer quantity;
    private RequestType requestType;
    private StatusType status;
    private String message;
}
