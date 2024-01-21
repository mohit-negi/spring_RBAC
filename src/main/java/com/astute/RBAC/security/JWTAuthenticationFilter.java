package com.astute.RBAC.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    public JWTGenerator jwtGenerator;
    @Autowired
    private MyUserDetailService myUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtFromRequest(request);
        if ((StringUtils.hasText(token)) && jwtGenerator.validateToken(token)) {
            String username = jwtGenerator.getUserNameFromJWT(token);
            log.debug("Username extracted from token: {}", username);
            System.out.println("Username extracted from token: {}"+ username);
            UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
            log.debug("UserDetails retrieved from UserDetailService: {}", userDetails);
            System.out.println("UserDetails retrieved from UserDetailService: {}"+ userDetails);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            System.out.println("Authentication successful for user: {}"+username);
            log.debug("Authentication successful for user: {}", username);

        }

        filterChain.doFilter(request, response);
    }


    private String getJwtFromRequest(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
        {
            return bearerToken.substring(8,bearerToken.length());

        }
        return null;
    }
}
