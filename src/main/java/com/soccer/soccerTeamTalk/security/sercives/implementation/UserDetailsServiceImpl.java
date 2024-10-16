package com.soccer.soccerTeamTalk.security.sercives.implementation;

import com.soccer.soccerTeamTalk.models.User;
import com.soccer.soccerTeamTalk.repository.UserRepository;
import com.soccer.soccerTeamTalk.security.sercives.implementation.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username);
       if(user == null) {
           throw new UsernameNotFoundException("username not found" + username);
       }
       return UserDetailsImpl.build(user);
    }
}
