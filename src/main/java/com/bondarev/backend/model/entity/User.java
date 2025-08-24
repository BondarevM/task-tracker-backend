package com.bondarev.backend.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.bondarev.backend.model.Constants.EMAIL;
import static com.bondarev.backend.model.Constants.ID;
import static com.bondarev.backend.model.Constants.PASSWORD;
import static com.bondarev.backend.model.Constants.USERNAME;
import static com.bondarev.backend.model.Constants.USERS;
import static com.bondarev.backend.model.Constants.USER_ID;


@Entity
@Table(name = USERS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = USERNAME)
    private String username;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = EMAIL)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = USER_ID)
    List<Task> tasks;

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
