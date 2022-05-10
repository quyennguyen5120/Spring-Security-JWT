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

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private UserEntity nguoigui;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private UserEntity nguoinhan;

    @Column(name = "time_send")
    private Date timeSend;

    @PrePersist
    public void prePersist(){
        this.timeSend = new Date();
    }
}
