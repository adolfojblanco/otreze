package es.otreze.dtos.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationResponseDTO implements Serializable {
    private String access_token;
}
