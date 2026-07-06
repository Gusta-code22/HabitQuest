package br.com.Gusta_code22.habitquest.repository;

import br.com.Gusta_code22.habitquest.domain.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    Optional<Habit> findById(Long id);
}
