package com.example.srpingsecurityjwt.Repositorty;

import com.example.srpingsecurityjwt.Dto.MessageDto;
import com.example.srpingsecurityjwt.Entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query("select new com.example.srpingsecurityjwt.Dto.MessageDto(m) from MessageEntity m where m.conversations.id=?1" +
            " order by m.timeSend ASC")
    List<MessageDto> getDtoByConverId(Long id);
}
