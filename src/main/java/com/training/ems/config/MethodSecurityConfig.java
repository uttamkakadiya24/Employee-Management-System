package com.training.ems.config;

import com.training.ems.permissions.CustomPermissionEvaluator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@AllArgsConstructor
@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig extends DefaultMethodSecurityExpressionHandler {

    private final CustomPermissionEvaluator customPermissionEvaluator;

    @Override
    protected PermissionEvaluator getPermissionEvaluator() {
        return customPermissionEvaluator;
    }
}
