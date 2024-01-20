package com.astute.RBAC.entities.repo;

import com.astute.RBAC.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
//    @Query("SELECT r ")
//    Role findByName(String roleAdmin);
    @Query("SELECT r FROM Role r WHERE r.name = :roleAdmin")
    Role findByName(@Param("roleAdmin") String roleAdmin);
//    Role saveIfNotExist(Role role);
}
