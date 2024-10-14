package com.soccer.soccerTeamTalk.security.jwt;

import com.soccer.soccerTeamTalk.security.sercives.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LogManager.getLogger(JwtAuthFilter.class.getName());


    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String username;
        final String jwtToken;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            System.out.println(authHeader);
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);
        logger.info("JWT Token: " + authHeader);
        username = jwtUtils.extractUsername(jwtToken); // create a methode that can extract the userEmail from the token (see the class JwtUtils)
        logger.debug("JWT Token: " + jwtToken);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtUtils.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                logger.info("Authentication succeed");
            }
            else {
                logger.error("Cannot set user authentication: {}");
            }
        }
        filterChain.doFilter(request, response);
    }

}
