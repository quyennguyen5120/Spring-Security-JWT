package com.example.srpingsecurityjwt.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conversation")
public class Conversations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private UserEntity fromUser;

    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private UserEntity toUser;
}
