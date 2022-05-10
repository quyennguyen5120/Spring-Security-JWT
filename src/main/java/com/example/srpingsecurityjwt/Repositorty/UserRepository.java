package com.example.srpingsecurityjwt.Repositorty;

import com.example.srpingsecurityjwt.Dto.UserDto;
import com.example.srpingsecurityjwt.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Query("select new com.example.srpingsecurityjwt.Dto.UserDto(u) from UserEntity u where u.id != ?1")
    List<UserDto> getAllNotInAuthen(Long userId);
}
