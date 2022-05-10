package com.example.srpingsecurityjwt.RestController;

import com.example.srpingsecurityjwt.Dto.UserDto;
import com.example.srpingsecurityjwt.Entity.UserEntity;
import com.example.srpingsecurityjwt.Repositorty.CustomDetailService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class RestUserController {

    @Secured({"ROLE_USER"})
    @GetMapping(value = "/get_current_user")
    public UserDto userDto(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomDetailService customDetailService = (CustomDetailService) authentication.getPrincipal();
        UserEntity userEntity = customDetailService.getUserEntity();
        return new UserDto(userEntity);
    }
}
