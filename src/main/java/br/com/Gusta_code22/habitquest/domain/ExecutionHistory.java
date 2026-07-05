package br.com.Gusta_code22.habitquest.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ExecutionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime hourHabit;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;
}
