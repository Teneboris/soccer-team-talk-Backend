package com.soccer.soccerTeamTalk.security.sercives.implementation;

import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private UserDetails userDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username);
       if(user == null) {
           throw new UsernameNotFoundException("username not found" + username);
       }
       return UserDetailsImpl.build(user);
    }

    public List<User> getAllUserWithoutLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("User not authenticated");
        }

        String loggedUsername = authentication.getName();

        List<User> users = userRepository.findAll();

        if (users == null || users.isEmpty()) {
            throw new RuntimeException("Users not found");
        }

        // Filter out the logged-in user from the list.
        return users.stream()
                .filter(user -> !user.getUsername().equals(loggedUsername))
                .collect(Collectors.toList());
    }

}
