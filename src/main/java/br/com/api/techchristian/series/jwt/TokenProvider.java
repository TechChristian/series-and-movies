package br.com.api.techchristian.series.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider {
    @Value("${jwt.expiration}")
    private Long expirationTime;

    @Value("${jwt.key}")
    private String key;

    // * generate token for user authenticated.
    private String generateToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return buildToken(user.getUsername());
    }

    // * build token
    private String buildToken(String username) {
        Date now =  new Date();
        Date expiration = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    // * return key and their methods.
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    // * extract all infos (claims) to do token.
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // * return username save in token
    private String getUsernameFromToken(String token) {
        return getClaimsFromToken(token)
                .getSubject();
    }

    // * verify if is token is valid (signing, format and expiration)
    public Boolean validateToken(String token) {
        try{
            getClaimsFromToken(token);
            return true;
        }catch (Exception ex){
            return false;
        }
    }


}
