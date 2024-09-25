package com.example.demo.service;

import com.example.demo.dto.ProfileCreateDTO;
import com.example.demo.dto.ProfileDTO;
import com.example.demo.dto.ProfileUpdateDTO;
import com.example.demo.dto.auth.RegistrationDTO;
import com.example.demo.entity.ProfileEntity;
import com.example.demo.exp.AppBadException;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.util.MD5Util;
import com.example.demo.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO create(ProfileCreateDTO dto) {

        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            log.warn("Email already exists email : {}", dto.getEmail());
            throw new AppBadException("Email already in use");
        }


        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.getMD5(dto.getPassword()));
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());

        profileRepository.save(entity);

        return toDTO(entity);
    }

    private ProfileDTO toDTO(ProfileEntity entity) {

        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private ProfileDTO toDTOUser(ProfileEntity entity) {

//        (id,name,surname,email,main_photo((url)))

        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());

        return dto;
    }

    public ProfileDTO update(ProfileUpdateDTO profileUpdateDTO) {
        ProfileEntity entity = SecurityUtil.getProfile();
        Objects.requireNonNull(entity);
        entity.setName(profileUpdateDTO.getName());
        entity.setSurname(profileUpdateDTO.getSurname());
        profileRepository.save(entity);
        return toDTOUser(entity);
    }

    public ProfileDTO updateForAdmin(ProfileDTO profile) {
        ProfileEntity entity = SecurityUtil.getProfile();
        Objects.requireNonNull(entity);


        Optional<ProfileEntity> optional = profileRepository.getProfileById(profile.getId());
        if (!optional.isPresent()) {
            log.warn("Profile not found : {}", profile.getId());
            throw new AppBadException("Profile not found");
        }

        ProfileEntity entity2 = new ProfileEntity();


        entity2.setId(profile.getId());
        entity2.setName(profile.getName());
        entity2.setSurname(profile.getSurname());
        entity2.setEmail(profile.getEmail());
        entity2.setStatus(profile.getStatus());
        entity2.setRole(profile.getRole());
        entity2.setCreatedDate(profile.getCreatedDate());
        entity2.setPassword(profile.getPassword());
        entity2.setVisible(profile.getVisible());


        profileRepository.save(entity2);
        return toDTOUser(entity2);
    }

    public Boolean changePassword(String oldPassword, String newPassword, String newPasswordRepeat) {

        if (!newPassword.equals(newPasswordRepeat)) {
            throw new AppBadException("Passwords do not match");
        }

        ProfileEntity entity = SecurityUtil.getProfile();
        Objects.requireNonNull(entity);

        if (!entity.getPassword().equals(MD5Util.getMD5(oldPassword))) {
            throw new AppBadException("Wrong password");
        }

        entity.setPassword(MD5Util.getMD5(newPassword));
        profileRepository.save(entity);
        return true;
    }


    public ProfileDTO deleteProfileById(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.getProfileById(id);
        if (!optional.isPresent()) {
            log.warn("Profile not found : {}", id);
            throw new AppBadException("Profile not found");
        }
        ProfileEntity entity = optional.get();
        profileRepository.deleteById(id.toString());
        return toDTOUser(entity);
    }
}
