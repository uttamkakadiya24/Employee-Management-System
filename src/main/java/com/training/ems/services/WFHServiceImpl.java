package com.training.ems.services;

import com.training.ems.dao.WfhRequestRepository;
import com.training.ems.dto.WfhRequestDto;
import com.training.ems.entities.WfhRequest;
import com.training.ems.mapper.WfhRequestMapper;
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
public class WFHServiceImpl implements WFHService{

    private final WfhRequestRepository wfhRequestRepository;
    private final AuthService authService;

    @Override
    public WfhRequestDto createWFHRequest(WfhRequestDto wfhRequestDto){
        WfhRequest wfhRequest = WfhRequestMapper.INSTANCE.toEntity(wfhRequestDto);
        authService.checkValidLoggedUser(wfhRequest.getEmployeeId());

        wfhRequest.setStatus(StatusType.PENDING);
        wfhRequestRepository.save(wfhRequest);
        return WfhRequestMapper.INSTANCE.toDto(wfhRequest);
    }

    @Override
    public WfhRequestDto approveWfhRequest(String managerId, String requestId) {
        WfhRequest wfhRequest = wfhRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request is NOT Found"));

        authService.checkValidLoggedUser(wfhRequest.getManagerId());

        if (wfhRequest.getStatus().equals(StatusType.APPROVED)){
            throw  new IllegalArgumentException("Request is already APPROVED");
        }

        wfhRequest.setStatus(StatusType.APPROVED);
        wfhRequestRepository.save(wfhRequest);
        return WfhRequestMapper.INSTANCE.toDto(wfhRequest);

    }

    @Override
    public WfhRequestDto rejectWfhRequest(String managerId, String requestId) {
        WfhRequest wfhRequest = wfhRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request is NOT Found"));

        authService.checkValidLoggedUser(wfhRequest.getManagerId());

        if (wfhRequest.getStatus().equals(StatusType.REJECTED)){
            throw  new IllegalArgumentException("Request is already REJECTED");
        }

        wfhRequest.setStatus(StatusType.REJECTED);
        wfhRequestRepository.save(wfhRequest);
        return WfhRequestMapper.INSTANCE.toDto(wfhRequest);
    }

    public List<WfhRequestDto> getAllWfhRequest() {

        Map<String ,List<WfhRequest>> wfhRequestMap = wfhRequestRepository.findAll().stream()
                .collect(Collectors.groupingBy(WfhRequest::getEmployeeId));

        List<WfhRequestDto> wfhRequestList = new ArrayList<>();

        for (Map.Entry<String, List<WfhRequest>> entry : wfhRequestMap.entrySet()) {

            List<WfhRequest> wfhRequests = entry.getValue();

            WfhRequestDto wfhRequestDto = new WfhRequestDto();

            for (WfhRequest wfhRequest : wfhRequests) {
                wfhRequestDto.setEmployeeId(wfhRequest.getEmployeeId());
                wfhRequestDto.setStartDate(wfhRequest.getStartDate());
                wfhRequestDto.setEndDate(wfhRequest.getEndDate());

            }
            wfhRequestList.add(wfhRequestDto);
        }
        return wfhRequestList;
    }
}
