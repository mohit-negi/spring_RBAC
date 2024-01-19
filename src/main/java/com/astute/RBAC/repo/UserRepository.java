package com.astute.RBAC.repo;

import com.astute.RBAC.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT * From user where user.email = ?1")
    User findByEmail(String email);
}
