package com.astute.RBAC.repo;

import java.util.Optional;

import com.astute.RBAC.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);

    boolean existsByUsername(String username);
}