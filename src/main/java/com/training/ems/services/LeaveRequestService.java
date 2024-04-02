package com.training.ems.services;

import com.training.ems.dto.LeaveRequestDto;

import java.util.List;

public interface LeaveRequestService {
    LeaveRequestDto createLeaveRequest(LeaveRequestDto leaveRequestDto);
    LeaveRequestDto approveLeaveRequest(String managerId, String requestId);
    LeaveRequestDto rejectLeaveRequest(String managerId, String requestId);
    List<LeaveRequestDto> getAllLeaveRequest();
}
