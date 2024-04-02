package com.training.ems.dao;

import com.training.ems.entities.Admin;
import com.training.ems.util.exception.EntityNotFoundByUsernameException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin ,String > {

//    public List<Request> getLeaveRequestByAdminId(String adminId);

    Optional<Admin> findByUsername(String username);
    default Admin findByUsernameOrThrow(String username){
        return findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundByUsernameException("Admin",username));
    }

}
