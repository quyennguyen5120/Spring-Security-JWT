package com.example.srpingsecurityjwt.WebConfiguration;

import com.example.srpingsecurityjwt.Repositorty.CustomDetailService;
import com.example.srpingsecurityjwt.Service.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserService userService;
    @Value("${jwt.SECRET_KEY}")
    private String JWT_SECRET;
    @Value("${jwt.JWT_EXPIRATION}")
    private int JWT_EXPIRATION;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
           try{
               String jwtToken = getJwtFromRequest(request);
               if(jwtToken != null){
                   String username = jwtProvider.getKeyByValueFromJWT("username", jwtToken);//Giải mã token lấy username
                   CustomDetailService userDetails = (CustomDetailService) userService.loadUserByUsername(username);
                   if(userDetails != null) {
                       // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
                       UsernamePasswordAuthenticationToken
                               authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                       authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                       SecurityContextHolder.getContext().setAuthentication(authentication);
                   }
               }
           }
           catch (Exception e){
               System.out.println(e);
           }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }

    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        }
        catch (SignatureException e){
            logger.error("Invalid JWT signature: "+ e.getMessage());
        }
        catch (MalformedJwtException e){
            logger.error("Invalid JWT token: "+ e.getMessage());
        }
        catch (ExpiredJwtException e){
            logger.error("JWT token is expired: "+ e.getMessage());
        }
        catch (UnsupportedJwtException e){
            logger.error("JWT token unsupported: "+ e.getMessage());
        }
        catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty: "+ e.getMessage());
        }
        return false;
    }
}
