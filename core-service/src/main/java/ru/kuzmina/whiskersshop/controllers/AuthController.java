package ru.kuzmina.whiskersshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.whiskersshop.api.dtos.JwtRequest;
import ru.kuzmina.whiskersshop.api.dtos.JwtResponse;
import ru.kuzmina.whiskersshop.api.dtos.StringResponse;
import ru.kuzmina.whiskersshop.services.UserService;
import ru.kuzmina.whiskersshop.utils.JwtTokenUtil;

import java.security.Principal;

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

    @GetMapping("/auth_check")
    public StringResponse authCheck(Principal principal) {
        return new StringResponse(principal.getName());
    }

}
