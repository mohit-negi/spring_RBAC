package com.astute.RBAC.services;

import com.astute.RBAC.models.ApplicationUser;
import com.astute.RBAC.models.enums.TokenType;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public interface IJwtService {
    String validateToken(String token, boolean isRefreshToken) throws JWTVerificationException;

    String generateToken(ApplicationUser user, TokenType tokenType);

    Algorithm generateAlgorithm(TokenType tokenType);
}