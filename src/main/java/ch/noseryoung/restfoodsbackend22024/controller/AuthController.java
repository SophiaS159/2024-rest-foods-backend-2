package ch.noseryoung.restfoodsbackend22024.controller;

import ch.noseryoung.restfoodsbackend22024.payload.JwtResponse;
import ch.noseryoung.restfoodsbackend22024.payload.LoginRequest;
import ch.noseryoung.restfoodsbackend22024.repository.UserRepository;
import ch.noseryoung.restfoodsbackend22024.security.JwtUtils;
import ch.noseryoung.restfoodsbackend22024.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        JwtResponse response = new JwtResponse(token, userDetails.getId(), userDetails.getUsername(), userDetails.getRole());

        return ResponseEntity.ok(response);
    }
}
