package com.training.ems.entities;

import com.training.ems.util.enums.RequestType;
import com.training.ems.util.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Deprecated
public class Request {

    @Id
    private String id;
    private String adminId;
    private String employeeId;
    private String startDate;
    private String endDate;
    private StatusType status;
    private RequestType requestType;
    private String message;

}
