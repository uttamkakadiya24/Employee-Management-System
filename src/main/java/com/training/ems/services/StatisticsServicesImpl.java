package com.training.ems.services;

import com.training.ems.dao.AttendanceRepository;
import com.training.ems.dto.StatsDto;
import com.training.ems.entities.Attendance;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class StatisticsServicesImpl implements StatisticsService {

    private final AttendanceRepository attendanceRepository;

    public List<StatsDto> getStats() {

        Map<String, List<Attendance>> attendanceMap = attendanceRepository.findAll()
                .stream()
                .filter(attendance -> attendance.getEmployee() != null)
                .collect(Collectors.groupingBy(attendance -> attendance.getEmployee().getId()));


//        Map<Employee,List<Attendance>> collect = new HashMap<>();
//        for (Attendance attendance: attendanceList) {
//            String id = attendance.getEmployee().getId();
//            List<Attendance> list = new ArrayList<>();
//            for (Attendance data: attendanceList) {
//                if(id.equalsIgnoreCase(data.getEmployee().getId())){
//                    list.add(data);
//                }
//            }
//            collect.put(id,list);
//        }

        List<StatsDto> returnData = new ArrayList<>();

        //ToDO: Contact gopal
        for (Map.Entry<String, List<Attendance>> entry : attendanceMap.entrySet()) {
            List<Attendance> attendance = entry.getValue();
            String id = entry.getKey();
            StatsDto statsDto = new StatsDto();

            for (int i = 0; i < attendance.size() - 1; i = i + 2) {
                DateTime inDate = new DateTime(attendance.get(i).getDate());
                DateTime outDate = new DateTime(attendance.get(i + 1).getDate());

                long diffInMillis = outDate.getMillis() - inDate.getMillis();
                int diffInHours = (int) TimeUnit.HOURS.convert(diffInMillis, TimeUnit.MILLISECONDS);

                if (diffInHours < 8) {
                    statsDto.setDate(attendance.get(i).getDate().toString());
                    statsDto.setId(id);
                }
            }
            returnData.add(statsDto);
        }

        return returnData;
    }

}


//        List<StatsDto> returnData = new ArrayList<>();
//
//        for (Map.Entry<String, List<Attendance>> entry : attendanceMap.entrySet()) {
//
//            String id = entry.getKey();
//            List<Attendance> attendanceList = entry.getValue();
//
//            LocalDate date = LocalDate.now();
//            LocalDateTime dateTime = date.toDateTimeAtStartOfDay().toLocalDateTime();
//
//            //group attendance by date
//            Map<Date, List<Attendance>> attendanceByDate = attendanceList.stream()
//                    .collect(Collectors.groupingBy(attendance -> attendance.getDate()));
//            for (Map.Entry<Date, List<Attendance>> dateEntry : attendanceByDate.entrySet()) {
//                List<Attendance> attendancesForDate = dateEntry.getValue();
//
//                // Calculate total hours for the Day
//                long totalMillis = 0;
//                for (int i = 0; i < attendancesForDate.size() - 1; i = i + 2){
//                    DateTime inDate = new DateTime(attendancesForDate.get(i).getDate());
//                    DateTime outDate = new DateTime(attendancesForDate.get(i+1).getDate());
//
//                    long diffInMillis = outDate.getMillis()-inDate.getMillis();
//
//                    totalMillis += diffInMillis;
//
//                }
//                int totalHours = (int)TimeUnit.HOURS.convert(totalMillis, TimeUnit.MILLISECONDS);
//
//                if (totalHours < 8 ) {
//                    StatsDto statsDto = new StatsDto();
//                    statsDto.setDate(dateEntry.getKey().toString());
//                    statsDto.setId(id);
//                    returnData.add(statsDto);
//                }
//            }
//        }
//
//        return returnData;
//    }