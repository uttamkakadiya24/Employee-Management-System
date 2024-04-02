package com.training.ems.entities;

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
@Document(collection = "Purchase")

public class Purchase extends SuperDocument {

    public String adminId;
    public String employeeId;
    public PeripheralRequest peripheralRequest;
    public StatusType status;

}
