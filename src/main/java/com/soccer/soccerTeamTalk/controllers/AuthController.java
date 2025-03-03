package com.soccer.soccerTeamTalk.controllers;

import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.playload.request.JwtResponseToken;
import com.soccer.soccerTeamTalk.playload.request.LoginRequest;
import com.soccer.soccerTeamTalk.playload.request.SignupRequest;
import com.soccer.soccerTeamTalk.playload.request.TokenRefreshRequest;
import com.soccer.soccerTeamTalk.playload.response.JwtResponse;
import com.soccer.soccerTeamTalk.playload.response.MessageResponse;
import com.soccer.soccerTeamTalk.security.jwt.JwtUtils;
import com.soccer.soccerTeamTalk.security.sercives.implementation.AuthServiceImpl;
import com.soccer.soccerTeamTalk.security.sercives.implementation.UserDetailsImpl;
import com.soccer.soccerTeamTalk.security.sercives.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        final UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        if(user != null){
            String jwt = jwtUtils.generateToken(user);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
             return ResponseEntity.ok(new JwtResponse(
                    jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        }
        return ResponseEntity.ok(MessageResponse.builder()
                .message("some error has occurred")
                .statusCode(400)
                .build());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        authService.UserRegister(signUpRequest);
        return ResponseEntity.ok(MessageResponse.builder()
                .message("User registered successfully!")
                .status(OK)
                .build());
    }

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userDetailsService.getAllUserWithoutLoggedUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/logged-username")
    public ResponseEntity<Map<String, String>> getLoggedUser() {
        Map<String, String> loggedUser = userDetailsService.getLoggedUsername();
        return ResponseEntity.ok(loggedUser);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        if (jwtUtils.validateRefreshToken(refreshToken)) {
            String username = jwtUtils.extractUsername(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String newAccessToken = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponseToken(newAccessToken, refreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }
}
