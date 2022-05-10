package com.example.srpingsecurityjwt.Repositorty;

import com.example.srpingsecurityjwt.Entity.Conversations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationsRepository extends JpaRepository<Conversations, Long> {
    @Query("select c from Conversations c where c.fromUser.id = ?1")
    List<Conversations> getByFromUser(Long userId);
}
