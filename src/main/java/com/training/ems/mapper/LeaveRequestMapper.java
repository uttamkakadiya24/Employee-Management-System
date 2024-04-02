package com.training.ems.mapper;

import com.training.ems.config.MapstructConfig;
import com.training.ems.dto.LeaveRequestDto;
import com.training.ems.entities.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface LeaveRequestMapper {
    LeaveRequestMapper INSTANCE = Mappers.getMapper(LeaveRequestMapper.class);
//    @Mapping(target = "requestId", ignore = true)
    LeaveRequest toEntity(LeaveRequestDto leaveRequestDto);
    LeaveRequestDto toDto(LeaveRequest leaveRequest);

    List<LeaveRequest> toEntity(List<LeaveRequestDto> leaveRequestDto);
    List<LeaveRequest> toDto(List<LeaveRequest> leaveRequest);

}
