package com.training.ems.dto;

import com.training.ems.util.enums.RequestType;
import com.training.ems.util.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDto  {

    private String managerId;
    private String employeeId;
    private String startDate;
    private String endDate;
    private StatusType status;
    private RequestType requestType;
    private String message;
}
