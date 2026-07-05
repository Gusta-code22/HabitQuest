package br.com.Gusta_code22.habitquest.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "creation_date")
    @CreatedDate
    private LocalDateTime creationDate;

    @Column
    private Integer streak = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "habit")
    private List<ExecutionHistory> executionHistories;

}
