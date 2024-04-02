package com.training.ems.permissions;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RolePermissionRepository extends MongoRepository<RolePermission,String> {

    List<RolePermission> findByRoleNameIn(List<String> roles);
}
