package com.training.ems.mapper;

import com.training.ems.config.MapstructConfig;
import com.training.ems.dto.WfhRequestDto;
import com.training.ems.entities.WfhRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface WfhRequestMapper {
    WfhRequestMapper INSTANCE = Mappers.getMapper(WfhRequestMapper.class);
//    @Mapping(target = "id",ignore = true)
    WfhRequest toEntity(WfhRequestDto wfhRequestDto);
    WfhRequestDto toDto(WfhRequest wfhRequest);
}
