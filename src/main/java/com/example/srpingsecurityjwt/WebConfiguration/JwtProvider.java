package com.example.srpingsecurityjwt.WebConfiguration;

import com.example.srpingsecurityjwt.Dto.LoginRespon;
import com.example.srpingsecurityjwt.Entity.UserEntity;
import com.example.srpingsecurityjwt.Repositorty.CustomDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Date;

@Component
public class JwtProvider {
    private final String JWT_SECRET = "quzxczxczxc";
    private final int JWT_EXPIRATION = 604000000;

    public String generateToken(CustomDetailService customDetailService){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(Long.toString(customDetailService.getUserEntity().getId()))
                .claim("username", customDetailService.getUsername())
                .claim("role", "ROLE_USER")
                .setExpiration(expiryDate)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public String getKeyByValueFromJWT(String key, String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.get(key,String.class);
    }

}
