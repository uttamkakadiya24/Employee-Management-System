package com.training.ems.mapper;

import com.training.ems.config.MapstructConfig;
import com.training.ems.dto.AttendanceDto;
import com.training.ems.entities.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapstructConfig.class)
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);
//    @Mapping(target = "employeeId", ignore = true)
    Attendance toEntity(AttendanceDto attendanceDto);
    AttendanceDto toDto(Attendance attendance);
}
