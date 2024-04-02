package com.training.ems.dao;

import com.training.ems.entities.WfhRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WfhRequestRepository extends MongoRepository<WfhRequest,String > {

}
