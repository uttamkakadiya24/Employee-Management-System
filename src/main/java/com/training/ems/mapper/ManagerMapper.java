package com.training.ems.mapper;

import com.training.ems.config.MapstructConfig;
import com.training.ems.dto.ManagerDto;
import com.training.ems.entities.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface ManagerMapper {
    ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);

    Manager toEntity(ManagerDto managerDto);
    ManagerDto toDto(Manager manager);

}
