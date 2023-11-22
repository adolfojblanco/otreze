package es.otreze.controllers;

import es.otreze.dtos.RegisteredUserDTO;
import es.otreze.dtos.SaveUserDTO;
import es.otreze.services.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping
    public ResponseEntity<RegisteredUserDTO> registerOne(@RequestBody @Valid SaveUserDTO newUser) {

        RegisteredUserDTO registeredUser = authenticationService.registerOneCustomer(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);

    }

}
