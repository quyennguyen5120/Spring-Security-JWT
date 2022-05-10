package com.example.srpingsecurityjwt.Dto;

import com.example.srpingsecurityjwt.Entity.UserEntity;
import lombok.Data;

@Data
public class LoginRespon {
    private String accessToken;
    private String tokenType;
    private String refreshToken;

    public LoginRespon(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
        this.refreshToken = refreshToken;

    }

}
