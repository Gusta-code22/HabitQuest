package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.domain.Habit;
import br.com.Gusta_code22.habitquest.domain.User;
import br.com.Gusta_code22.habitquest.dto.HabitRequestDTO;
import br.com.Gusta_code22.habitquest.dto.HabitResponseDTO;
import br.com.Gusta_code22.habitquest.repository.HabitRepository;
import br.com.Gusta_code22.habitquest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitResponseDTO createHabit(HabitRequestDTO requestDTO) throws Exception {
        User user = userRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new Exception("User Not Found"));
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

    public HabitResponseDTO findById(Long id) throws Exception {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new Exception("Habit Not Found"));



        return new HabitResponseDTO(
                habit.getId(), habit.getName(), habit.getDescription(), habit.getStreak(), habit.getCreationDate(),
                habit.getUser().getId()
        );
    }
}
