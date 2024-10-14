package com.soccer.soccerTeamTalk.controllers;

import com.soccer.soccerTeamTalk.models.ERole;
import com.soccer.soccerTeamTalk.models.Role;
import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.playload.request.JwtResponseToken;
import com.soccer.soccerTeamTalk.playload.request.LoginRequest;
import com.soccer.soccerTeamTalk.playload.request.SignupRequest;
import com.soccer.soccerTeamTalk.playload.request.TokenRefreshRequest;
import com.soccer.soccerTeamTalk.playload.response.JwtResponse;
import com.soccer.soccerTeamTalk.playload.response.MessageResponse;
import com.soccer.soccerTeamTalk.repository.RoleRepository;
import com.soccer.soccerTeamTalk.repository.UserRepository;
import com.soccer.soccerTeamTalk.security.jwt.JwtUtils;
import com.soccer.soccerTeamTalk.security.sercives.UserDetailsImpl;
import com.soccer.soccerTeamTalk.security.sercives.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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
        return ResponseEntity.status(400).body("some error has occurred");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail());
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "player":
                        Role modRole = roleRepository.findByName(ERole.ROLE_PLAYER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    case "coach":
                        Role coach = roleRepository.findByName(ERole.ROLE_COACH)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(coach);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
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
