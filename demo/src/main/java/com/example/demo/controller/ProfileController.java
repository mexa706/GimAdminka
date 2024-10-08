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
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/current/update") //USER
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProfileDTO> updateForUser(@Valid @RequestBody ProfileUpdateDTO profile) {
        return ResponseEntity.ok().body(profileService.update(profile));
    }

    @PutMapping("/adm/update/{id}") // ADMIN
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProfileDTO> updateForAdmin(@PathVariable Integer id, @Valid @RequestBody ProfileDTO profile) {
        profile.setId(id);
        return ResponseEntity.ok().body(profileService.updateForAdmin(profile));
    }

    @PutMapping("/current/change-password") //USER
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Boolean> changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String newPasswordRepeat) {
        return ResponseEntity.ok().body(profileService.changePassword(oldPassword, newPassword, newPasswordRepeat));
    }

    @DeleteMapping("/adm/delete/{id}") //ADMIN
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ProfileDTO> delete(@PathVariable Integer id) {
        return ResponseEntity.ok().body(profileService.deleteProfileById(id));
    }

    @GetMapping("/adm/getAllUser")  //ADMIN
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<ProfileDTO>> getAllUser() {
        return ResponseEntity.ok().body(profileService.getAllUser());
    }

  /*  @GetMapping("/adm/all_with_pagination") //ADMIN
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<PageImpl<ProfileDTO>> getAllWithPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<ProfileDTO> pageList = profileService.getAllWithPagination(page - 1, size);
        return ResponseEntity.ok().body(pageList);
    }*/


}
