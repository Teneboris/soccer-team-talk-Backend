package com.soccer.soccerTeamTalk.security.sercives;

import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.playload.request.SignupRequest;

public interface AuthService {

    public void UserRegister(SignupRequest signUpRequest);
}
