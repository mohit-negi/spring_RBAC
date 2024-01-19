package com.astute.RBAC.services.impl;

import com.astute.RBAC.dto.response.UserResponseDTO;
import com.astute.RBAC.models.mapper.ModelMapperData;
import com.astute.RBAC.repo.IUserRepository;
import com.astute.RBAC.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<UserResponseDTO> loadAllUsers() {
        return ModelMapperData.mapMany(userRepository.findAll(), UserResponseDTO.class);
    }

}