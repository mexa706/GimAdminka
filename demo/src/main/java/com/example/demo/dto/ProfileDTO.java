package com.example.demo.dto;

import com.example.demo.enums.ProfileRole;
import com.example.demo.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class ProfileDTO {

    private final String id= UUID.randomUUID().toString();
    private String name;
    private String surname;
    private String email;
    private String password;
    private ProfileRole role ;
    private ProfileStatus status;
    private Boolean visible;
    private LocalDateTime createdDate;
    private String jwt;
}
