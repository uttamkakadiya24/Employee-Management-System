package com.training.ems.entities;

import com.training.ems.util.enums.Permission;
import com.training.ems.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserCoreObject extends SuperDocument{
    private Role role;
    private List<Permission> permissionList;
}
