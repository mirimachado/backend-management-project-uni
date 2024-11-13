package br.com.project.backend.uni.security.jwt;

import br.com.project.backend.uni.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${unibackend.jwtSecret}")
    private String jwtSecret;

    @Value("${unibackend.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetail){
        return Jwts.builder().setSubject(userDetail.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512).compact();
    }

    public Key getSigninKey(){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }

    public String getUsernameToken(String token){
        return Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody().getSubject();

    }

    public boolean validateJwtToken(String authToken){
         try {
             Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
             return true;
         }catch (MalformedJwtException e){
             System.out.println("Token inválido: " + e.getMessage());
         }catch (ExpiredJwtException e){
             System.out.println("Token expirado: " + e.getMessage());
         }catch (UnsupportedJwtException e){
             System.out.println("Token não suportado: " + e.getMessage());
         }catch (IllegalArgumentException e){
             System.out.println("Argumento inválido: " + e.getMessage());
         }
              return false;
    }


}
