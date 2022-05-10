package com.example.srpingsecurityjwt.RestController;

import com.example.srpingsecurityjwt.Dto.ConversationsDto;
import com.example.srpingsecurityjwt.Dto.MessageDto;
import com.example.srpingsecurityjwt.Dto.SendContentDto;
import com.example.srpingsecurityjwt.Dto.UserDto;
import com.example.srpingsecurityjwt.Entity.Conversations;
import com.example.srpingsecurityjwt.Entity.MessageEntity;
import com.example.srpingsecurityjwt.Entity.UserEntity;
import com.example.srpingsecurityjwt.Repositorty.ConversationsRepository;
import com.example.srpingsecurityjwt.Repositorty.CustomDetailService;
import com.example.srpingsecurityjwt.Repositorty.MessageRepository;
import com.example.srpingsecurityjwt.Repositorty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chat")
@CrossOrigin
public class RestChatController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConversationsRepository conversationsRepository;

    @Secured({"ROLE_USER"})
    @GetMapping(value = "/get_all")
    public List<UserDto> getAllUserNotAuThen(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomDetailService customDetailService = (CustomDetailService) authentication.getPrincipal();
        UserEntity userEntity = customDetailService.getUserEntity();
        List<UserDto> userDtoList = userRepository.getAllNotInAuthen(userEntity.getId());
        return userDtoList;
    }

    @Secured({"ROLE_USER"})
    @GetMapping(value = "/get_conversiton/{currentId}/{friendId}")
    public ConversationsDto getAllUserNotAuThen(@PathVariable("currentId")Long currentId, @PathVariable("friendId")Long friendId){
        ConversationsDto conversationsDto = conversationsRepository.getByFromUser(currentId,friendId);
        if(conversationsDto == null){
            Conversations conversations = new Conversations();
            UserEntity fromUser = userRepository.getById(currentId);
            UserEntity toUser = userRepository.getById(friendId);
            conversations.setToUser(toUser);
            conversations.setFromUser(fromUser);
            conversations = conversationsRepository.save(conversations);
            conversationsDto = new ConversationsDto(conversations);
        }
        else{
            List<MessageDto> messageDtos = messageRepository.getDtoByConverId(conversationsDto.getId());
            if(messageDtos != null && messageDtos.size() > 0){
                conversationsDto.setMessages(messageDtos);
            }
        }
        return conversationsDto;
    }
    @Secured({"ROLE_USER"})
    @PostMapping(value = "/send_content")
    public MessageDto send_content(@RequestBody SendContentDto sendContentDto){
        MessageEntity messageEntity = new MessageEntity();
        UserEntity senderId = userRepository.getById(sendContentDto.getSenderId());
        Conversations conversations = conversationsRepository.getById(sendContentDto.getConversationId());
        messageEntity.setContent(sendContentDto.getContent());
        messageEntity.setSender(senderId);
        messageEntity.setConversations(conversations);
        messageEntity = messageRepository.save(messageEntity);
        return new MessageDto(messageEntity);
    }



}
