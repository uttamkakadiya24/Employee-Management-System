package com.training.ems.mapper;

import com.training.ems.config.MapstructConfig;
import com.training.ems.dto.EmployeeDto;
import com.training.ems.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
//    @Mapping(target = "employeeId", ignore = true)
    Employee toEntity(EmployeeDto employeeDto);
    EmployeeDto toDto(Employee employee);

    List<Employee> toEntity(List<EmployeeDto> employeeDtoList);
    List<EmployeeDto> toDto(List<Employee> employeeList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "peripheralRequestList", ignore = true)
    void updateEntity(EmployeeDto employeeDto, @MappingTarget Employee employee);

}
