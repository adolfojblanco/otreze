package es.otreze.controllers;

import es.otreze.dtos.auth.AuthenticationRequestDTO;
import es.otreze.dtos.auth.AuthenticationResponseDTO;
import es.otreze.persistence.security.User;
import es.otreze.services.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticationResponse(
            @RequestBody @Valid AuthenticationRequestDTO authenticationRequest) {

        AuthenticationResponseDTO resp = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String token) {
        boolean isTokenValid = authenticationService.validateToken(token);
        return ResponseEntity.ok(isTokenValid);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile() {
        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }
}
