package com.fiap.fiap_tech_challenge.infrastructure.adapters.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.domain.exception.InvalidOrExpiredToken;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key = Keys.hmacShaKeyFor("uma-chave-muito-grande-e-secreta-substitua-por-render".getBytes()); // use env var
    private final long validityMillis = 1000L * 60 * 60 * 2; // 24h

    public String generateToken(User user) throws JsonProcessingException {
        Date now = new Date();
        Date exp = new Date(now.getTime() + validityMillis);
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        // Converte o objeto em JSON String
        String jsonString = mapper.writeValueAsString(user);
        System.out.println(jsonString);
        return Jwts.builder()
                .setSubject(jsonString)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public User validateAndGetUsername(String token) {
        try {
            Jws<Claims> parsed = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

            System.out.println(parsed.getBody().getSubject());
            return mapper.readValue(parsed.getBody().getSubject(), User.class);


        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidOrExpiredToken("Token Invalid"); // token inválido/expirado

        } catch (JsonProcessingException e) {
            throw new InvalidOrExpiredToken("Format json invalid");
        }
    }
}
