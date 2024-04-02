package com.training.ems.dao;

import com.training.ems.entities.LeaveRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface LeaveRequestRepository extends MongoRepository<LeaveRequest,String> {

    List<LeaveRequest> findByEmployeeId(List<LeaveRequest> leaveRequestList);

}
