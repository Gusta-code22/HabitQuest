package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.domain.ExecutionHistory;
import br.com.Gusta_code22.habitquest.domain.Habit;
import br.com.Gusta_code22.habitquest.domain.User;
import br.com.Gusta_code22.habitquest.repository.ExecutionHistoryRepository;
import br.com.Gusta_code22.habitquest.repository.HabitRepository;
import br.com.Gusta_code22.habitquest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutionHistoryService {

    private final ExecutionHistoryRepository historyRepository;
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public void registrarExecucao(Long habitId) throws Exception {

        LocalDateTime today = LocalDateTime.now();

        thatItWasAlreadyExecutedToday(habitId);

        updateStreak(habitId);

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new Exception("Hábit not found"));

        rewardUser(habit);

        ExecutionHistory history = new ExecutionHistory();
        history.setHabit(habit);
        history.setHourHabit(today);
        historyRepository.save(history);

    }

    private void thatItWasAlreadyExecutedToday(Long id) throws Exception {

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

        boolean alreadyExecutedToday = historyRepository.existsByHabitAndHourHabitBetween(
                id, startOfDay, endOfDay
        );

        if (alreadyExecutedToday) {
            throw new Exception("You have already completed this habit today! Come back tomorrow.");
        }
    }

    private void updateStreak(Long habitId) throws Exception {

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new Exception("Hábit not found"));

        List<ExecutionHistory> executionHistories = historyRepository.findLastExecutionByHabitId(habitId);

        LocalDate yesterday = LocalDate.now().minusDays(1);

        // If he just created Habit
        if (executionHistories.isEmpty()){
            habit.setStreak(1);
        } else {
            // If the last check-in date was yesterday, another streak for the account
            LocalDate dateLastExecutor = executionHistories.getFirst().getHourHabit().toLocalDate();
            if (dateLastExecutor.equals(yesterday)){
                habit.setStreak(habit.getStreak() + 1);
            } else {
                // if the last chek-in was more than a day away, reset the streak
                habit.setStreak(1);
            }
        }
        habitRepository.save(habit);
    }
    private void rewardUser(Habit habit){

        User user = habit.getUser();

        Integer xpGanho = 10 + (habit.getStreak() * 2);

        user.setXp(user.getXp() + xpGanho);

        Integer xpRequiredLevel = user.getLevel() * 100;

        if (user.getXp() >= xpRequiredLevel) {
            user.setLevel(user.getLevel() + 1);

        }

        userRepository.save(user);

    }
}
