package com.example.srpingsecurityjwt.RestController;

import com.example.srpingsecurityjwt.Dto.MessageDto;
import com.example.srpingsecurityjwt.Dto.UserDto;
import com.example.srpingsecurityjwt.Repositorty.CustomDetailService;
import com.example.srpingsecurityjwt.Repositorty.MessageRepository;
import com.example.srpingsecurityjwt.Repositorty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chat")
public class RestChatController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    @Secured({"ROLE_USER"})
    @GetMapping(value = "/get_all")
    List<UserDto> getAllUserNotAuThen(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomDetailService customDetailService = (CustomDetailService) authentication;
        List<UserDto> userDtoList = userRepository.findAll().stream().map(x->new UserDto(x)).collect(Collectors.toList());
        return userDtoList;
    }
}
