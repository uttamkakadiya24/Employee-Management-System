package com.training.ems.util.enums;

import com.training.ems.util.enums.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN,
    MANAGER,
    EMPLOYEE

}
