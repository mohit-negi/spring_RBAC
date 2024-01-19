package com.astute.RBAC.services;

import com.astute.RBAC.dto.request.LoginRequestDTO;
import com.astute.RBAC.dto.request.RegistrationRequestDTO;
import com.astute.RBAC.dto.response.BaseResponseDTO;
import com.astute.RBAC.dto.response.UserResponseDTO;
import com.astute.RBAC.exception.AuthenticatedFailureException;
import com.astute.RBAC.models.ApplicationUser;
import com.astute.RBAC.models.Role;
import com.astute.RBAC.models.enums.TokenType;
import com.astute.RBAC.models.mapper.ModelMapperData;
import com.astute.RBAC.repo.IRoleRepository;
import com.astute.RBAC.repo.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IJwtService jwtService;

    @Override
    public Optional<BaseResponseDTO> login(LoginRequestDTO dto) {

        if (!userRepository.existsByUsername(dto.getUsername()))
            throw new AuthenticatedFailureException("Incorrect username or password!");

        ApplicationUser user = userRepository.findByUsername(dto.getUsername()).get();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
            UserResponseDTO responseDTO = ModelMapperData.mapOne(user, UserResponseDTO.class);

            String accessToken = jwtService.generateToken(user, TokenType.ACCESS_TOKEN);
            String refreshToken = jwtService.generateToken(user, TokenType.REFRESH_TOKEN);

            return Optional.of(new BaseResponseDTO(responseDTO, accessToken, refreshToken));
        } catch (Exception e) {
            throw new AuthenticatedFailureException("Incorrect username or password!");
        }
    }

    @Override
    public Optional<BaseResponseDTO> refreshToken(Map<String, Object> body) {

        String refreshToken = body.get("refreshToken").toString();

        if (refreshToken == null)
            throw new AuthenticatedFailureException("No refresh token found!");

        String username = jwtService.validateToken(refreshToken, true);

        ApplicationUser user = userRepository.findByUsername(username).get();
        UserResponseDTO dto = ModelMapperData.mapOne(user, UserResponseDTO.class);

        String newAccessToken = jwtService.generateToken(user, TokenType.ACCESS_TOKEN);
        String newRefreshToken = jwtService.generateToken(user, TokenType.REFRESH_TOKEN);

        return Optional.of(new BaseResponseDTO(dto, newAccessToken, newRefreshToken));
    }

    @Override
    public Optional<UserResponseDTO> register(RegistrationRequestDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername()))
            throw new AuthenticatedFailureException("Username already exist!");

        if (!dto.getPassword().equals(dto.getRepeatPassword()))
            throw new AuthenticatedFailureException("Confirm password not match!");

        ApplicationUser user = new ApplicationUser();
        user.setId(0L);
        user.setFirstname(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER").get();

        Collection<Role> roles = new HashSet<>();
        roles.add(userRole);

        user.setRoles(roles);

        return Optional.of(
                ModelMapperData.mapOne(userRepository.save(user), UserResponseDTO.class));
    }

}