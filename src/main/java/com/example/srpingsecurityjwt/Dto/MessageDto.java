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
    private UserDto nguoigui;
    private UserDto nguoinhan;
    private Date timeSend;

    public MessageDto(MessageEntity messageEntity){
        this.id = messageEntity.getId();
        this.content = messageEntity.getContent();
        this.nguoigui = new UserDto(messageEntity.getNguoigui());
        this.nguoinhan = new UserDto(messageEntity.getNguoinhan());
        this.timeSend = messageEntity.getTimeSend();
    }
}
