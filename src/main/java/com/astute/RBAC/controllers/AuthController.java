package com.astute.RBAC.controllers;

import com.astute.RBAC.DTO.AuthResponseDTO;
import com.astute.RBAC.DTO.LoginDTO;
import com.astute.RBAC.DTO.RegisterDTO;
import com.astute.RBAC.entities.Role;
import com.astute.RBAC.entities.UserEntity;
import com.astute.RBAC.repo.RoleRepo;
import com.astute.RBAC.repo.UserRepo;
import com.astute.RBAC.security.JWTGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if(userRepo.existsByUserName(registerDTO.getUserName())){
            log.info("User name already exists");
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUserName(registerDTO.getUserName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Role role = roleRepo.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(role));
        userRepo.save(user);
        return new ResponseEntity<>("User Registration Success !",HttpStatus.OK);
    }
    @PostMapping("/user/login")
    public ResponseEntity<AuthResponseDTO> loginFunc(@RequestBody LoginDTO loginDTO){
        System.out.println("=====================================>>>>>>>>>>>>> HEllo");
        log.info("=====================================>>>>>>>>>>>>> HEllo");
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGenerator.generateToken(authentication);
        System.out.println("================================================="+token);
            return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.ACCEPTED);

    }
}
