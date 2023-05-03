package com.pilot.controller;

import com.pilot.exceptions.NotFoundException;
import com.pilot.models.RequestLogin;
import com.pilot.models.ResponseData;
import com.pilot.models.ResponseJWT;
import com.pilot.repos.UserRepository;
import com.pilot.security.JwtProvider;
import com.pilot.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserServices userService;

    @PostMapping("/signin")
    public ResponseEntity<ResponseJWT> authenticateUser(@Valid @RequestBody RequestLogin requestLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestLogin.getEmail(), requestLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        userRepository.findByEmail(requestLogin.getEmail()).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(ResponseJWT.builder()
                .token(jwt)
                .type("Bearer")
                .build());
    }

    @PostMapping("/insert/user")
    public ResponseData<?> insert(@RequestBody @Valid @NotNull RequestLogin requestLogin) {
        return userService.insert(requestLogin);
    }

}