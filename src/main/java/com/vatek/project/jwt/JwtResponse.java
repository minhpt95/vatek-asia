package com.catdev.project.jwt;


import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class JwtResponse {
    private Long id;
    private String accessToken;
    private String type = "Bearer";
    private String username;
    private String email;
    private String refreshToken;
    private List<String> roles;

    public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
