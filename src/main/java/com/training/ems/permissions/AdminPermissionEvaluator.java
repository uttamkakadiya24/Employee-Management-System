package com.training.ems.permissions;

import com.training.ems.util.exception.MethodNotSupportedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class AdminPermissionEvaluator extends AbstractPermissionEvaluator {

    public AdminPermissionEvaluator(
            CustomPermissionEvaluator customPermissionEvaluator
    ) {
        customPermissionEvaluator.addEvaluator("ADMIN",this);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        return super.hasPermission(authentication,targetDomainObject,permission);
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
