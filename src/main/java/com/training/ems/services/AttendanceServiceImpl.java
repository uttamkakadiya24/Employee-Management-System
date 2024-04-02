package com.training.ems.services;

import com.training.ems.dao.AttendanceRepository;
import com.training.ems.dao.EmployeeRepository;
import com.training.ems.dto.AttendanceDto;
import com.training.ems.entities.Attendance;
import com.training.ems.mapper.AttendanceMapper;
import com.training.ems.securityService.AuthService;
import com.training.ems.util.enums.AttendanceType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final AuthService authService;

    @Override
    public AttendanceDto employeeEntry(String employeeId) {

        String loggedEmployeeId = authService.getLoggedInUserId();

        if (!(employeeId.equals(loggedEmployeeId))) {
            throw new RuntimeException("Entry only by logged in employee");
        }

        validateWithLastEntry(employeeId, AttendanceType.IN);

        Attendance attendance = new Attendance();
        attendance.setEmployee(
            employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee Not Found By Id :"+employeeId))
        );

        attendance.setType(AttendanceType.IN);
        attendance.setDate(new Date());
        attendance = attendanceRepository.save(attendance);

        return AttendanceMapper.INSTANCE.toDto(attendance);
    }

    @Override
    public AttendanceDto employeeExit(String employeeId) {

        String loggedEmployeeId = authService.getLoggedInUserId();

        if (!(employeeId.equals(loggedEmployeeId))) {
            throw new RuntimeException("Exit only by logged in employee");
        }

        validateWithLastEntry(employeeId, AttendanceType.OUT);

        Attendance attendance = new Attendance();
        attendance.setEmployee(
                employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new RuntimeException("Employee Not Found By Id :"+employeeId))
        );

        attendance.setType(AttendanceType.OUT);
        attendance.setDate(new Date());

        
        attendance = attendanceRepository.save(attendance);

        return AttendanceMapper.INSTANCE.toDto(attendance);
    }

    private void validateWithLastEntry(String employeeId, AttendanceType type) {
        Attendance lastAttendance = attendanceRepository.findFirst1ByEmployeeIdOrderByDateDesc(employeeId);

        if(Objects.nonNull(lastAttendance) && lastAttendance.getType().equals(type)) {
            throw new RuntimeException("Employee last entry was "+type+" fuck off.");
        }
    }
}
