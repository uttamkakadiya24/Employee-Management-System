package com.training.ems.mapper;

import com.training.ems.config.MapstructConfig;
import com.training.ems.dto.PeripheralRequestDto;
import com.training.ems.entities.PeripheralRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface PeripheralRequestMapper {
    PeripheralRequestMapper INSTANCE = Mappers.getMapper(PeripheralRequestMapper.class);
//    @Mapping(target = "id",ignore = true)
    PeripheralRequest toEntity(PeripheralRequestDto peripheralRequestDto);
    PeripheralRequestDto toDto(PeripheralRequest peripheralRequest);
}
