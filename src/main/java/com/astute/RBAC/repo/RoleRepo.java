package com.astute.RBAC.repo;

import com.astute.RBAC.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
