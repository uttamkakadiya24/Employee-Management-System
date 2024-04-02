package com.training.ems.securityService;

import com.training.ems.entities.*;
import com.training.ems.security.JwtHelper;
import com.training.ems.util.exception.InvalidCredentialException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final JwtHelper helper;


    public void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authManager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new InvalidCredentialException("Invalid Credential!!");
        }
    }

    public JwtResponse getResponse(JwtRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        return JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
    }

    public String getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user = authentication.getPrincipal();

        if(user instanceof Admin) {  return ((Admin) user).getId(); }
        else if(user instanceof Employee) { return ((Employee) user).getId(); }
        else if (user instanceof Manager) { return ((Manager) user).getId(); }

        throw new RuntimeException("User is Not Recognised");
    }

}
