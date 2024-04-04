package com.training.ems.dto;

import com.training.ems.entities.UserCoreObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto extends UserCoreObject {

    private String name;
    private String username;
    private String password;

}
