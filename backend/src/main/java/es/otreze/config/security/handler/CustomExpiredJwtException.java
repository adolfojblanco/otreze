package es.otreze.config.security.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;

public class CustomExpiredJwtException extends ExpiredJwtException {


    public CustomExpiredJwtException(Header header, Claims claims, String message) {
        super(header, claims, message);

    }

    public CustomExpiredJwtException(Header header, Claims claims, String message, Throwable cause) {
        super(header, claims, message, cause);


    }
}
