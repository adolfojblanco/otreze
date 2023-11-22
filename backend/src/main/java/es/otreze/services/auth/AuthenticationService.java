package es.otreze.services.auth;

import es.otreze.dtos.RegisteredUserDTO;
import es.otreze.dtos.SaveUserDTO;
import es.otreze.dtos.auth.AuthenticationRequestDTO;
import es.otreze.dtos.auth.AuthenticationResponseDTO;
import es.otreze.exceptions.ObjectNotFoundException;
import es.otreze.persistence.security.User;
import es.otreze.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtServices jwtServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    public RegisteredUserDTO registerOneCustomer(SaveUserDTO newUser) {
        User user = userService.registerOneCustomer(newUser);

        RegisteredUserDTO userDTO = new RegisteredUserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRoles().toString());

        String jwt = jwtServices.generateToken(user, jwtServices.generateExtraClaims(user));
        userDTO.setJwt(jwt);
        return userDTO;
    }

    /* Login user */
    public AuthenticationResponseDTO login(AuthenticationRequestDTO auth) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword());
        authenticationManager.authenticate((authentication));

        User user = userService.findOneByUsername(auth.getUsername()).orElseThrow();
        String access_token = jwtServices.generateToken(user, jwtServices.generateExtraClaims(user));
        AuthenticationResponseDTO autRes = new AuthenticationResponseDTO();
        autRes.setAccess_token(access_token);
        return autRes;
    }


    /* Validate token */
    public boolean validateToken(String token) {
        try{
            jwtServices.extractUsername(token);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* Get user logged */
    public User findLoggedInUser() {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        return userService.findOneByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found " + username));
    }
}
