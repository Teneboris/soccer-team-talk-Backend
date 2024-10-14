package com.soccer.soccerTeamTalk.playload.request;

import lombok.Getter;
import lombok.Setter;

public class JwtResponseToken {

    @Getter
    @Setter
    private String accessToken;
    @Getter
    @Setter
    private String refreshToken;

    public JwtResponseToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
