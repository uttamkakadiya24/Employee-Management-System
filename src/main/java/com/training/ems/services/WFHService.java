package com.training.ems.services;

import com.training.ems.dto.WfhRequestDto;

import java.util.List;

public interface WFHService {
    WfhRequestDto createWFHRequest(WfhRequestDto wfhRequestDto);
    WfhRequestDto approveWfhRequest(String managerId, String requestId);
    WfhRequestDto rejectWfhRequest(String managerId, String requestId);
    List<WfhRequestDto> getAllWfhRequest();
}
