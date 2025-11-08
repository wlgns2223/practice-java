package org.example.commerce.authentification;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Component
public class JwtService {

    private final Key key;
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
}
