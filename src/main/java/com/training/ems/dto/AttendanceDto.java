package com.training.ems.dto;

import com.training.ems.entities.Employee;
import com.training.ems.util.enums.AttendanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    private AttendanceType type;
    private Date date;

    @DocumentReference(collection = "employee")
    private Employee employee;
}
