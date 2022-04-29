package com.example.srpingsecurityjwt.Repositorty;

import com.example.srpingsecurityjwt.Entity.RefreshToken;
import com.example.srpingsecurityjwt.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Override
    Optional<RefreshToken> findById(Long id);
    Optional<RefreshToken> findByToken(String token);
    @Query("select u from RefreshToken u where u.user.username LIKE ?1")
    RefreshToken findByUsername(String username);
    int deleteByUser(UserEntity user);
}
