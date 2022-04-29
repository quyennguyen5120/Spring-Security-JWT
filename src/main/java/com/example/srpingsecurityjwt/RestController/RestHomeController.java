package com.example.srpingsecurityjwt.RestController;

import com.example.srpingsecurityjwt.Dto.LoginRespon;
import com.example.srpingsecurityjwt.Entity.RefreshToken;
import com.example.srpingsecurityjwt.Entity.UserEntity;
import com.example.srpingsecurityjwt.Repositorty.CustomDetailService;
import com.example.srpingsecurityjwt.Repositorty.UserRepository;
import com.example.srpingsecurityjwt.Service.RefreshTokenService;
import com.example.srpingsecurityjwt.Service.UserService;
import com.example.srpingsecurityjwt.WebConfiguration.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
public class RestHomeController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity userEntity){
        //Check loggin
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userEntity.getUsername(),
                        userEntity.getPassword()
                )
        );
        //Nếu thành công sẽ set vào context
        CustomDetailService customDetailService = (CustomDetailService) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(customDetailService);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(customDetailService.getUsername());

        return ResponseEntity.ok(new LoginRespon(jwt, refreshToken.getToken()));
    }
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshToken request) {
        String requestRefreshToken = request.getToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtProvider.generateTokenByUsername(user.getUsername());
                    return ResponseEntity.ok(new LoginRespon(token, requestRefreshToken));
                })
                .orElseThrow(null);
    }

    @GetMapping("/add")
    public String addNewAccount(){
        UserEntity user = new UserEntity(null,"admin",passwordEncoder.encode("admin"), null);
        userRepository.save(user);
        return null;
    }

    @GetMapping("/auth/test")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("ROLE_USER");
    }

    @GetMapping("/auth/testz")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> testz(){
        return ResponseEntity.ok("ROLE_ADMIN");
    }


}
