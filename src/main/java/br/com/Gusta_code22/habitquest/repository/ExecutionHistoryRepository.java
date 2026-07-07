package br.com.Gusta_code22.habitquest.repository;

import br.com.Gusta_code22.habitquest.domain.ExecutionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExecutionHistoryRepository extends JpaRepository<ExecutionHistory, Long> {

    boolean existsByHabitIdAndHourHabitBetween(Long habitId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT e FROM ExecutionHistory e WHERE e.habit.id = :habitId ORDER BY e.hourHabit DESC")
    List<ExecutionHistory> findLastExecutionByHabitId(@Param("habitId") Long habitId);
}
