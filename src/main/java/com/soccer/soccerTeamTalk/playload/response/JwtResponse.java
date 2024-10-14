package com.soccer.soccerTeamTalk.playload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JwtResponse {
    @Setter
    @Getter
    private String token;
    private String type = "Bearer";

    @Setter
    @Getter
    private String id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    private List<String> roles;

    public JwtResponse(String accessToken, String id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

}

