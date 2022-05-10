package com.example.srpingsecurityjwt.Repositorty;

import com.example.srpingsecurityjwt.Dto.ConversationsDto;
import com.example.srpingsecurityjwt.Entity.Conversations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationsRepository extends JpaRepository<Conversations, Long> {
    @Query("select new com.example.srpingsecurityjwt.Dto.ConversationsDto(c) from Conversations c where c.fromUser.id = ?1 and c.toUser.id=?2")
    ConversationsDto getByFromUser(Long currentId , Long friendId);
}
