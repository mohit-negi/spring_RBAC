package com.astute.RBAC.services;

import com.astute.RBAC.dto.response.UserResponseDTO;

public interface IAdminService {
    Iterable<UserResponseDTO> loadAllUsers();
}