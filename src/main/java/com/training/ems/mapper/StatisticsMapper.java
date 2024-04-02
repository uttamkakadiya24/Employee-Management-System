package com.training.ems.mapper;

import com.training.ems.config.MapstructConfig;
import com.training.ems.dto.StatsDto;
import com.training.ems.entities.Stats;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Deprecated
@Mapper(config = MapstructConfig.class)
public interface StatisticsMapper {
     StatisticsMapper INSTANCE = Mappers.getMapper(StatisticsMapper.class);
//     @Mapping(target = "id", ignore = true)
     Stats toEntity(StatsDto statsDto);
     StatsDto toDto(Stats stats);

     List<Stats> toEntity(List<StatsDto> statsDtoList);
     List<StatsDto> toDto(List<Stats> stats);
}
