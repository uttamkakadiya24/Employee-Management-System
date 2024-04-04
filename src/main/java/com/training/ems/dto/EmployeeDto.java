package com.training.ems.dto;

import com.training.ems.entities.PeripheralRequest;
import com.training.ems.entities.UserCoreObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDto extends UserCoreObject {

    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private List<PeripheralRequest> peripheralRequestList;

}
