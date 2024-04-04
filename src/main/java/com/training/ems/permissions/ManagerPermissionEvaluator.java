package com.training.ems.permissions;

import com.training.ems.util.enums.Role;
import com.training.ems.util.exception.MethodNotSupportedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class ManagerPermissionEvaluator extends AbstractPermissionEvaluator {

    private final AdminPermissionEvaluator adminPermissionEvaluator;

    public ManagerPermissionEvaluator (
            CustomPermissionEvaluator customPermissionEvaluator,
            AdminPermissionEvaluator adminPermissionEvaluator
    ) {
        this.adminPermissionEvaluator = adminPermissionEvaluator;

        customPermissionEvaluator.addEvaluator("MANAGER",this);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (adminPermissionEvaluator.hasPermission(authentication, Role.ADMIN,permission)) {
            return true;
        }
        return super.hasPermission(authentication, targetDomainObject, permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission
    ) {
        throw new MethodNotSupportedException();
    }
}
