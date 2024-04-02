package com.training.ems.dao;

import com.training.ems.entities.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttendanceRepository extends MongoRepository<Attendance, String> {
    Attendance findFirst1ByEmployeeIdOrderByDateDesc(String employee);
}






