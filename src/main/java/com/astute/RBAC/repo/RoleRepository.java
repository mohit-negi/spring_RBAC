//package com.astute.RBAC.repo;
//
//import com.astute.RBAC.models.Role;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.Collection;
//
//public interface RoleRepository extends JpaRepository<Role,Long> {
//    @Query("SELECT * From role where role.name = ?1")
//    Role findByName(String name);
//}
