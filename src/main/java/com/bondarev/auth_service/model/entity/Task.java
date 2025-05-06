package com.bondarev.auth_service.model.entity;

import com.bondarev.auth_service.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

import static com.bondarev.auth_service.model.Constants.*;

@Entity
@Table(name = TASKS)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = TITLE)
    private String title;

    @Column(name = TEXT)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = STATUS)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = USER_ID)
    private User user;

    @Column(name = DONE_AT)
    private Instant doneAt;
}
