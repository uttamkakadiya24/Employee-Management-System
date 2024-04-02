package com.training.ems.entities;

import com.training.ems.util.enums.InventoryType;
import com.training.ems.util.enums.RequestType;
import com.training.ems.util.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PeripheralRequest")
public class PeripheralRequest extends SuperDocument {

    private String managerId;
    private String employeeId;
    private InventoryType inventoryType;
    private Integer quantity;
    private RequestType requestType;
    private StatusType status;
    private String message;

}
