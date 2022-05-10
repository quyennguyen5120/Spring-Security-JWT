package com.example.srpingsecurityjwt.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "time_send")
    private Date timeSend;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private UserEntity sender;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Conversations conversations;

    @PrePersist
    public void setTimeSend(){
        this.timeSend = new Date();
    }



}
