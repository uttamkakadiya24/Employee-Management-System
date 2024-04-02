package com.training.ems.entities;

import com.training.ems.util.enums.InventoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Inventory")

public class Inventory extends SuperDocument {

    private String managerId;
    private Integer quantity;
    private InventoryType inventoryType;
    private List<PeripheralRequest> peripheralRequestList;
}
