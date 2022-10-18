package ru.kuzmina.wiskersshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.wiskersshop.model.dtos.JwtRequest;
import ru.kuzmina.wiskersshop.model.dtos.JwtResponse;
import ru.kuzmina.wiskersshop.services.UserService;
import ru.kuzmina.wiskersshop.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
         try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword()));
         } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }
         UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
         String token =jwtTokenUtil.generateToken(userDetails);
         return ResponseEntity.ok(new JwtResponse(token));
    }

}
