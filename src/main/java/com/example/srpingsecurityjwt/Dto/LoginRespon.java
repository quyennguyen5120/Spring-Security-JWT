package com.example.srpingsecurityjwt.Dto;

import com.example.srpingsecurityjwt.Entity.UserEntity;
import lombok.Data;

@Data
public class LoginRespon {
    private String accessToken;
    private String tokenType;

    public LoginRespon(String accessToken) {
        this.accessToken = "Bearer " + accessToken;
        this.tokenType = "Bearer";
    }

}
