package com.training.ems.permissions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;

    public List<RolePermission> getPermission(List<String> roles, String name) {
        List<RolePermission> permissions = rolePermissionRepository.findByRoleNameIn(roles);

        permissions = permissions.stream()
                .filter(rolePermission -> rolePermission.getTarget().equals(name)).collect(Collectors.toList());
        return permissions;
    }

}
