package es.otreze.services.auth;


import es.otreze.persistence.security.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServices {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;
    @Value("${security.jwt.security-key}")
    private String SECRET_KEY;

    /** Generate extraClaims for token **/
    public Map<String, Object > generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getFullName());
        extraClaims.put("username", user.getUsername());
        extraClaims.put("roles", user.getAuthorities().toString());
        //extraClaims.put("authorities", user.getRoles().toString());
        return extraClaims;
    }

    public String generateToken(UserDetails user, Map<String, Object> claims) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims = claims;
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_IN_MINUTES * 60 * 1000) + issuedAt.getTime());
        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(generateKey())
                .header().add("typ","JWT")
                .and()
                .compact();
    }

    private Key generateKey() {
        byte[] key = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(key);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith((SecretKey) generateKey()).build().parseSignedClaims(token).getPayload();
    }
}
