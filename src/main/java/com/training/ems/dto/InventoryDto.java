package com.training.ems.dto;

import com.training.ems.entities.PeripheralRequest;
import com.training.ems.util.enums.InventoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    private String managerId;
    private Integer quantity;
    private InventoryType inventoryType;
    private List<PeripheralRequest> peripheralRequestList;
}
