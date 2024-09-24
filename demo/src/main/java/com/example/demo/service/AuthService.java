package com.example.demo.service;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.dto.auth.LoginDTO;
import com.example.demo.entity.ProfileEntity;
import com.example.demo.enums.ProfileRole;
import com.example.demo.enums.ProfileStatus;
import com.example.demo.exp.AppBadException;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.util.JWTUtil;
import com.example.demo.util.MD5Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;


    public ProfileDTO login(LoginDTO authDTO) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(authDTO.getEmail());
        if (optional.isEmpty()) {
            throw new AppBadException("User not found");
        }

        ProfileEntity entity = optional.get();
       if (entity.getRole().equals(ProfileRole.ROLE_ADMIN)||entity.getRole().equals(ProfileRole.ROLE_TRENER)){

           if (!entity.getPassword().equals(authDTO.getPassword())) {
               throw new AppBadException("Wrong password");
           }
       }else{
           if (!entity.getPassword().equals(MD5Util.getMD5(authDTO.getPassword()))) {
               throw new AppBadException("Wrong password");
           }
       }


        if (entity.getStatus() != ProfileStatus.ACTIVE) {
            throw new AppBadException("User is not active");
        }

        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        dto.setStatus(entity.getStatus());
        dto.setJwt(JWTUtil.encode(entity.getId().toString(),entity.getEmail(), entity.getRole()));
        return dto;
    }
}
