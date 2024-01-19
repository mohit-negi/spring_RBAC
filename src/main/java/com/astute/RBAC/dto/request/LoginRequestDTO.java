package com.astute.RBAC.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginRequestDTO {
    private String username;
    private String password;
}