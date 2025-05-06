package com.bondarev.auth_service.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

import static com.bondarev.auth_service.model.Constants.*;

@Entity
@Table(name = USERS)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = LOGIN)
    private String login;

    @Column(name = PASSWORD)
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = USER_ID)
    Collection<Task> tasks;
}
