package com.training.ems.dto;

import com.training.ems.permissions.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {

    private String name;
    private String username;
    private String password;
    private Role role;

}
