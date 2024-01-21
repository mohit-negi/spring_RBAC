package com.astute.RBAC.repo;

import com.astute.RBAC.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUserName(String username);
    Boolean existsByUserName(String username);
}
