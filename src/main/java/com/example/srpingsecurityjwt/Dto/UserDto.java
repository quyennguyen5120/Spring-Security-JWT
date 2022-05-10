package com.example.srpingsecurityjwt.Dto;

import com.example.srpingsecurityjwt.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Boolean status;

    public UserDto(UserEntity userEntity){
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.status = userEntity.getStautus();
    }
}
