package com.bondarev.backend.model.entity;

import com.bondarev.backend.model.enums.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import static com.bondarev.backend.model.Constants.DONE_AT;
import static com.bondarev.backend.model.Constants.ID;
import static com.bondarev.backend.model.Constants.STATUS;
import static com.bondarev.backend.model.Constants.TASKS;
import static com.bondarev.backend.model.Constants.TEXT;
import static com.bondarev.backend.model.Constants.TITLE;
import static com.bondarev.backend.model.Constants.USER_ID;

@Entity
@Table(name = TASKS)
@Getter
@Setter
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
    private User owner;

    @Column(name = DONE_AT)
    private Instant doneAt;

    @Override
    public String toString() {
        return "Task{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", text='" + text + '\'' +
               ", status=" + status +
               '}';
    }
}
