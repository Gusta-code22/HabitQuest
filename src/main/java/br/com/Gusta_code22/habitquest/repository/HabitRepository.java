package br.com.Gusta_code22.habitquest.repository;

import br.com.Gusta_code22.habitquest.domain.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    @Query("""
        SELECT h FROM Habit h 
        WHERE h.id NOT IN (
            SELECT eh.habit.id FROM ExecutionHistory eh 
            WHERE eh.hourHabit >= :inicioDoDia AND eh.hourHabit <= :fimDoDia
        )
    """)
    List<Habit> findHabitsWithoutExecutionToday(
            @Param("inicioDoDia") LocalDateTime inicioDoDia,
            @Param("fimDoDia") LocalDateTime fimDoDia
    );
}
