package com.training.ems.permissions;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final Map<String, PermissionEvaluator> evaluatorMap = new HashMap<>();

    public void addEvaluator(String targetType, PermissionEvaluator evaluator){
        evaluatorMap.put(targetType, evaluator);
    }

    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
//        Object loggedInUser = auth.getPrincipal();

        if (!(evaluatorMap.containsKey(targetDomainObject.toString()))) {
            throw new IllegalArgumentException("target domain object not having evaluator");
        }
        return evaluatorMap
                .get(targetDomainObject.toString())
                .hasPermission(auth,targetDomainObject,permission);
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        return evaluatorMap.get(targetType).hasPermission(auth,targetId,targetType,permission);
    }

}
