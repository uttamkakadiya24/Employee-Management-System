package com.training.ems.services;

import com.training.ems.dao.LeaveRequestRepository;
import com.training.ems.dto.LeaveRequestDto;
import com.training.ems.entities.LeaveRequest;
import com.training.ems.mapper.LeaveRequestMapper;
import com.training.ems.securityService.AuthService;
import com.training.ems.util.enums.StatusType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class LeaveRequestServiceImpl implements LeaveRequestService {

//    @Autowired
    private final LeaveRequestRepository leaveRequestRepository;
    private final AuthService authService;

    @Override
    public LeaveRequestDto createLeaveRequest(LeaveRequestDto leaveRequestDto){
        LeaveRequest leaveRequest = LeaveRequestMapper.INSTANCE.toEntity(leaveRequestDto);
        authService.checkValidLoggedUser(leaveRequest.getEmployeeId());

        leaveRequest.setStatus(StatusType.PENDING);
        leaveRequestRepository.save(leaveRequest);
        return LeaveRequestMapper.INSTANCE.toDto(leaveRequest);
    }

    @Override
    public LeaveRequestDto approveLeaveRequest(String managerId, String requestId){
        LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request is Not Found"));

        authService.checkValidLoggedUser(leaveRequest.getManagerId());

        if (leaveRequest.getStatus().equals(StatusType.APPROVED)){
            throw  new IllegalArgumentException("Request is already APPROVED");
        }

        leaveRequest.setStatus(StatusType.APPROVED);
        leaveRequestRepository.save(leaveRequest);

        return LeaveRequestMapper.INSTANCE.toDto(leaveRequest);
    }

    @Override
    public LeaveRequestDto rejectLeaveRequest(String managerId, String requestId){

        LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request is Not Found"));

        authService.checkValidLoggedUser(leaveRequest.getManagerId());

        if (leaveRequest.getStatus().equals(StatusType.REJECTED)){
            throw  new IllegalArgumentException("Request is already REJECTED");
        }

        leaveRequest.setStatus(StatusType.REJECTED);
        leaveRequestRepository.save(leaveRequest);

        return LeaveRequestMapper.INSTANCE.toDto(leaveRequest);
    }

    public List<LeaveRequestDto> getAllLeaveRequest() {

        Map<String ,List<LeaveRequest>> leaveRequestMap = leaveRequestRepository.findAll().stream()
                .collect(Collectors.groupingBy(LeaveRequest::getEmployeeId));

        List<LeaveRequestDto> leaveRequestList = new ArrayList<>();

        for (Map.Entry<String, List<LeaveRequest>> entry : leaveRequestMap.entrySet()) {

            List<LeaveRequest> leaveRequests = entry.getValue();

            LeaveRequestDto leaveRequestDto = new LeaveRequestDto();

            for (LeaveRequest leaveRequest : leaveRequests) {
                leaveRequestDto.setEmployeeId(leaveRequest.getEmployeeId());
                leaveRequestDto.setStartDate(leaveRequest.getStartDate());
                leaveRequestDto.setEndDate(leaveRequest.getEndDate());
            }
            leaveRequestList.add(leaveRequestDto);
        }
        return leaveRequestList;
    }
}
