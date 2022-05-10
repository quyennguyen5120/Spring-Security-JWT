package com.example.srpingsecurityjwt.Dto;

import com.example.srpingsecurityjwt.Entity.MessageEntity;
import com.example.srpingsecurityjwt.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private String content;
    private UserDto sender;
    private Date time_send;

    public MessageDto(MessageEntity messageEntity){
        this.id = messageEntity.getId();
        this.content = messageEntity.getContent();
        this.sender = new UserDto(messageEntity.getSender());
        this.time_send = messageEntity.getTimeSend();
    }

}
