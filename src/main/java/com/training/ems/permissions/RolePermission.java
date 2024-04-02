package com.training.ems.permissions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {

    private String roleName;
    private String permission;
    private String target;
    private boolean read;
    private boolean create;
    private boolean update;
    private boolean delete;

    public boolean getRead() {
        return read;
    }
    public boolean getCreate() {
        return create;
    }
    public boolean getUpdate() {
        return update;
    }
    public boolean getDelete() {
        return delete;
    }

}
