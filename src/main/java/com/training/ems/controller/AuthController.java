package com.training.ems.controller;

import com.training.ems.entities.JwtRequest;
import com.training.ems.entities.JwtResponse;
import com.training.ems.securityService.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

//    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        authService.doAuthenticate(request.getUsername(), request.getPassword());

        JwtResponse response = authService.getResponse(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

