package com.example.demo.dto.auth;

import com.example.demo.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JwtDTO {
    private String id;
    private String username;
    private ProfileRole role;

    public JwtDTO(String id) {
        this.id = id;
    }

    public JwtDTO(String id, String username, ProfileRole role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
