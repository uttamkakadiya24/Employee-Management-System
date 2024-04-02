package com.training.ems.permissions;

import lombok.AllArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final RolePermissionService rolePermissionService;
    @Override
    public boolean hasPermission(Authentication auth, Object target, Object permission) {
        if (auth == null || target == null || !(permission instanceof String)) {
            return false;
        }
        if (!(target instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, (String) target,permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetId == null) || (targetType == null) || (!(permission instanceof String))) {
            return false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(),permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, String target, String permission) {

        var rolePermissions = rolePermissionService.getPermission(extractRoles(auth),target);

        if (rolePermissions.isEmpty()) {
            return false;
        }

        for (RolePermission rolePermission : rolePermissions) {
            var isGranted = switch (permission) {

                case "READ" :
                    yield rolePermission.getRead();
                case "CREATE" :
                    yield rolePermission.getCreate();
                case "UPDATE" :
                    yield rolePermission.getUpdate();
                case "DELETE" :
                    yield rolePermission.getDelete();
                default:
                    yield false;
            };
            if (Boolean.TRUE.equals(isGranted)) {
                return true;
            }
        }
        return false;
    }

    private List<String> extractRoles(Authentication auth) {
        return auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    }
}
