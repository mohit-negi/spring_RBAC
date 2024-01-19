package com.astute.RBAC.services;

import com.astute.RBAC.dto.request.LoginRequestDTO;
import com.astute.RBAC.dto.request.RegistrationRequestDTO;
import com.astute.RBAC.dto.response.BaseResponseDTO;
import com.astute.RBAC.dto.response.UserResponseDTO;

import java.util.Map;
import java.util.Optional;

public interface IAuthService {
    Optional<BaseResponseDTO> login(LoginRequestDTO dto);

    Optional<UserResponseDTO> register(RegistrationRequestDTO dto);

    Optional<BaseResponseDTO> refreshToken(Map<String, Object> body);
}