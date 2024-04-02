package com.training.ems.entities;

import com.training.ems.util.enums.RequestType;
import com.training.ems.util.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "WfhRequest")
public class WfhRequest extends SuperDocument {

    private String managerId;
    private String employeeId;
    private String startDate;
    private String endDate;
    private StatusType status;
    private RequestType requestType;
    private String message;

}
