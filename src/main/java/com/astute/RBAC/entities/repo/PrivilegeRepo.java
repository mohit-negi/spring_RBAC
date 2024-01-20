package com.astute.RBAC.entities.repo;

import com.astute.RBAC.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepo extends JpaRepository< Privilege,Integer> {
//    Privilege findByName(String name);
    @Query("SELECT r FROM Privilege r WHERE r.name = :name")
    Privilege findByName(@Param("name") String name);
//    Privilege saveIfNotExist(Privilege privilege);
}
