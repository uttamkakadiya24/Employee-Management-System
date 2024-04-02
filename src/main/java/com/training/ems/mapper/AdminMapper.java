package com.training.ems.mapper;

import com.training.ems.config.MapstructConfig;
import com.training.ems.dto.AdminDto;
import com.training.ems.entities.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);
//    @Mapping(target = "adminId", ignore = true)
    Admin toEntity(AdminDto adminDto);
    AdminDto toDto(Admin admin);
}
