package com.example.demo.controller;

import com.example.demo.dto.ProfileCreateDTO;
import com.example.demo.dto.ProfileDTO;
import com.example.demo.dto.ProfileUpdateDTO;
import com.example.demo.dto.auth.RegistrationDTO;
import com.example.demo.enums.ProfileRole;
import com.example.demo.service.ProfileService;
import com.example.demo.util.SecurityUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm/create") // ADMIN
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profile) {
        ProfileDTO response = profileService.create(profile);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/current/update")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProfileDTO> update(@Valid @RequestBody ProfileUpdateDTO profile) {
        return ResponseEntity.ok().body(profileService.update(profile));
    }


}
