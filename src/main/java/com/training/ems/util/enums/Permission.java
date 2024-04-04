package com.training.ems.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    READ,
    UPDATE,
    CREATE,
    DELETE
}
