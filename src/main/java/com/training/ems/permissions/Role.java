package com.training.ems.permissions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_DELETE,
                    Permission.MANAGER_READ,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_CREATE,
                    Permission.MANAGER_DELETE
            )
    ),
    MANAGER(
            Set.of(
                    Permission.MANAGER_READ,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_CREATE,
                    Permission.MANAGER_DELETE
            )
    ),
    EMPLOYEE(
            Set.of(
                    Permission.EMPLOYEE_READ,
                    Permission.EMPLOYEE_UPDATE,
                    Permission.EMPLOYEE_CREATE,
                    Permission.EMPLOYEE_DELETE
            )
    )

    ;
    public final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = new java.util.ArrayList<>(getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name().toUpperCase()));
        return authorities;
    }

}
