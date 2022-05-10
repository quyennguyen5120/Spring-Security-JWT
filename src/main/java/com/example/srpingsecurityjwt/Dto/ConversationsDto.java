package com.example.srpingsecurityjwt.Dto;

import com.example.srpingsecurityjwt.Entity.Conversations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationsDto {

    private Long id;
    private UserDto fromUser;
    private UserDto toUser;
    private List<MessageDto> messages;

    public ConversationsDto(Conversations conversations){
        this.id = conversations.getId();
        this.fromUser = new UserDto(conversations.getFromUser());
        this.toUser = new UserDto(conversations.getToUser());
    }

    public ConversationsDto(Conversations conversations, List<MessageDto> messages){
        this.id = conversations.getId();
        this.fromUser = new UserDto(conversations.getFromUser());
        this.toUser = new UserDto(conversations.getToUser());
        this.messages = messages;
    }
}
