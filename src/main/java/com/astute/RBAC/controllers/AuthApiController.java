package com.astute.RBAC.controllers;

import java.util.HashMap;
import java.util.Map;

import com.astute.RBAC.dto.request.LoginRequestDTO;
import com.astute.RBAC.dto.request.RegistrationRequestDTO;
import com.astute.RBAC.dto.response.UserResponseDTO;
import com.astute.RBAC.exception.AuthenticatedFailureException;
import com.astute.RBAC.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth/")
public class AuthApiController {

    @Autowired
    private IAuthService authService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        if (dto.getUsername().isEmpty() || dto.getPassword().isEmpty())
            throw new AuthenticatedFailureException("Empty username or password!");

        return ResponseEntity.ok().body(authService.login(dto));
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDTO dto) {
        // URI uri = URI
        // .create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/register").toUriString());
        try {
            return new ResponseEntity<UserResponseDTO>(authService.register(dto).get(), HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok().body(authService.refreshToken(body));
    }
}