package edu.school21.todoapp.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String info;
    @Column(nullable = false)
    private LocalDateTime createdOn;
    private LocalDateTime deadline;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;



    public Long getUserId() {
        return user != null ? user.getId() : null;
    }


    public String getFormattedCreatedOn() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
        return createdOn.format(formatter);
    }

    public String getFormattedDeadline() {
        if (deadline != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
            return deadline.format(formatter);
        }
        return "Без дедлайна";
    }
}
