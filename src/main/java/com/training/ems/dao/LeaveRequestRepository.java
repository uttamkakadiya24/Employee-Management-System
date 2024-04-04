package com.training.ems.dao;

import com.training.ems.entities.LeaveRequest;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LeaveRequestRepository extends MongoRepository<LeaveRequest,String> {
}
