package com.example.srpingsecurityjwt.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendContentDto {
    private Long senderId;
    private String content;
    private Long conversationId;
}
