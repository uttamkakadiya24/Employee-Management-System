package com.training.ems.entities;

import com.training.ems.util.enums.AttendanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "attendance")
public class Attendance extends SuperDocument {

    private AttendanceType type;
    private Date date;

    @DocumentReference(collection = "employee")
    private Employee employee;

}
