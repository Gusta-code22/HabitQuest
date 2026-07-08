package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.domain.Habit;
import br.com.Gusta_code22.habitquest.domain.User;
import br.com.Gusta_code22.habitquest.dto.HabitRequestDTO;
import br.com.Gusta_code22.habitquest.dto.HabitResponseDTO;
import br.com.Gusta_code22.habitquest.exception.HabitNotFoundException;
import br.com.Gusta_code22.habitquest.exception.UserNotFoundException;
import br.com.Gusta_code22.habitquest.repository.HabitRepository;
import br.com.Gusta_code22.habitquest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitResponseDTO createHabit(HabitRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Habit habit = new Habit();
        habit.setName(requestDTO.name());
        habit.setDescription(requestDTO.description());
        habit.setUser(user);
        habitRepository.save(habit);
        return new HabitResponseDTO(
                habit.getId(), habit.getName(), habit.getDescription(), habit.getStreak(), habit.getCreationDate(),
                habit.getUser().getId()
        );
    }

    public void deleteUser(Long id){
        habitRepository.deleteById(id);
    }

    public HabitResponseDTO findById(Long id) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new HabitNotFoundException("Habit Not Found"));



        return new HabitResponseDTO(
                habit.getId(), habit.getName(), habit.getDescription(), habit.getStreak(), habit.getCreationDate(),
                habit.getUser().getId()
        );

    }


}
