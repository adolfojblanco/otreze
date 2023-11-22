package es.otreze.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUserDTO implements Serializable {

    private UUID id;

    private String fullName;

    private String email;

    private String username;

    private String password;

    private String role;

    private String jwt;
}
