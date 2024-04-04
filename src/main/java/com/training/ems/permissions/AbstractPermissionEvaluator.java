package com.training.ems.permissions;

import com.training.ems.entities.UserCoreObject;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public abstract class AbstractPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        UserCoreObject userWithRoleAndPermission = (UserCoreObject) authentication.getPrincipal();
        return userWithRoleAndPermission.getRole().toString().equals(targetDomainObject)
                && userWithRoleAndPermission.getPermissionList().stream().anyMatch(perm -> perm.toString().equals(permission));
    }

    abstract public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission);
}
