package com.training.ems.services;

import com.training.ems.dto.AttendanceDto;

public interface AttendanceService {
    AttendanceDto employeeEntry(String id);
    AttendanceDto employeeExit(String id);
}
