package org.example.commerce.authentification;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Slf4j
@Component
public class JwtService {

    private final SecretKey key;
    private final Duration accessTtl;

    public JwtService(@Value("${security.jwt.secret}") String b64,
                      @Value("${security.jwt.ttl}") long ttl){
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(b64));
        this.accessTtl = Duration.ofMinutes(ttl);
    }

    public String createAccessToken(String subject, Map<String,Object> claims){
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(accessTtl)))
                .signWith(key)
                .compact();

    }

    public String createAccessToken(String subject){
        return createAccessToken(subject, null);

    }

    public Claims parseToken(final String token){
        try{
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            log.info("expired do something...");
            throw new RuntimeException(e.getMessage());

        } catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
