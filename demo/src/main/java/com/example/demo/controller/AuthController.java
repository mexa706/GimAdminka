package com.example.demo.controller;

import com.example.demo.dto.ProfileDTO;
import com.example.demo.dto.auth.LoginDTO;
import com.example.demo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Api list for Auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login", description = "Api for auth Login")
    @PostMapping("/login")
    public HttpEntity<ProfileDTO> loginUser(@RequestBody LoginDTO loginDto) {
        ProfileDTO result = authService.login(loginDto);
        return ResponseEntity.ok().body(result);
    }

}
