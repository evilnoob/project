package ru.evilnoob.project.auth.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClock;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {

    //private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.time}")
    private Integer jwtExpiration;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    private final Clock clock = DefaultClock.INSTANCE;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails subject) {
        Map<String, Object> claims = new HashMap<>();
        final Date expirationDate = calculateExpirationDate(clock.now());
        return doGenerateToken(claims, subject, expirationDate);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + jwtExpiration * 1000);
    }

    private String doGenerateToken(Map<String, Object> claims, UserDetails subject, Date expirationDate) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject.getUsername())
                .setIssuedAt(clock.now())
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }
}
