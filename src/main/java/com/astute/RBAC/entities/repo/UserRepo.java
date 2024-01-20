package com.astute.RBAC.entities.repo;

import com.astute.RBAC.entities.Role;
import com.astute.RBAC.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

//    User saveIfNotExist(User user);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
//    User findByEmail(String email);
}
