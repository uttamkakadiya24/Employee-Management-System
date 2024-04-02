package com.training.ems.config;

import org.mapstruct.Builder;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.WARN,
        builder = @Builder(disableBuilder = true),
        componentModel = MappingConstants.ComponentModel.DEFAULT
)
public class MapstructConfig {
}
