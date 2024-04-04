package com.training.ems.permissions;

import com.training.ems.util.enums.Role;
import com.training.ems.util.exception.MethodNotSupportedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class EmployeePermissionEvaluator extends AbstractPermissionEvaluator {

    private final AdminPermissionEvaluator adminPermissionEvaluator;

    public EmployeePermissionEvaluator (
            CustomPermissionEvaluator customPermissionEvaluator,
            AdminPermissionEvaluator adminPermissionEvaluator
    ) {
        this.adminPermissionEvaluator = adminPermissionEvaluator;
        customPermissionEvaluator.addEvaluator("EMPLOYEE",this);
    }

    @Override
    public boolean hasPermission(
            Authentication authentication,
            Object targetDomainObject,
            Object permission
    ) {
        // Check user have admin permissions
        if (adminPermissionEvaluator.hasPermission(authentication, Role.ADMIN, permission)) {
            return true;
        }
        return super.hasPermission(authentication, targetDomainObject, permission);
    }

    @Override
    public boolean hasPermission(
            Authentication authentication,
            Serializable targetId,
            String targetType,
            Object permission
    ) {
        throw new MethodNotSupportedException();
    }
}
