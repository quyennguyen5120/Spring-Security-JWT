package com.example.srpingsecurityjwt.RestController;

import com.example.srpingsecurityjwt.Dto.MessageDto;
import com.example.srpingsecurityjwt.Repositorty.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chat")
public class RestChatController {
    @Autowired
    MessageRepository messageRepository;


}
