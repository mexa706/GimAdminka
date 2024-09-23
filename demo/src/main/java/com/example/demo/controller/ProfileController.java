package com.example.demo.controller;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.enums.ProfileRole;
import com.example.demo.service.ProfileService;
import com.example.demo.util.SecurityUtil;
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



}
