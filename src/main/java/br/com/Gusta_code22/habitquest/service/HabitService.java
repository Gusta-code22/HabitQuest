package br.com.Gusta_code22.habitquest.service;

import br.com.Gusta_code22.habitquest.domain.Habit;
import br.com.Gusta_code22.habitquest.domain.User;
import br.com.Gusta_code22.habitquest.dto.HabitRequestDTO;
import br.com.Gusta_code22.habitquest.dto.HabitResponseDTO;
import br.com.Gusta_code22.habitquest.exception.ForbiddenException;
import br.com.Gusta_code22.habitquest.exception.HabitNotFoundException;
import br.com.Gusta_code22.habitquest.exception.UserNotFoundException;
import br.com.Gusta_code22.habitquest.repository.HabitRepository;
import br.com.Gusta_code22.habitquest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitResponseDTO createHabit(HabitRequestDTO requestDTO, JwtAuthenticationToken token) {
        Long currentUserId = Long.valueOf(token.getName());
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if (!Objects.equals(user.getId(), currentUserId)){
            throw new ForbiddenException("You cannot create a habit for another player");
        }


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

    public void deleteHabit(Long id, JwtAuthenticationToken token){
        Long currentUserId = Long.valueOf(token.getName());
        var habit = habitRepository.findById(id);
        if (habit.isEmpty()){
            throw new HabitNotFoundException("Habit not found");
        }

        if (!Objects.equals(habit.get().getUser().getId(), currentUserId)){
            throw new ForbiddenException("You are not allowed to delete this habit");
        }
        habitRepository.deleteById(id);
    }

    public HabitResponseDTO findById(Long id, JwtAuthenticationToken token) {
        Long currentHabitId = Long.valueOf(token.getName());
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit Not Found"));

        if (!Objects.equals(habit.getUser().getId(), currentHabitId)){
            throw new ForbiddenException("You cannot search for another Habit");
        }

        return new HabitResponseDTO(
                habit.getId(), habit.getName(), habit.getDescription(), habit.getStreak(), habit.getCreationDate(),
                habit.getUser().getId()
        );

    }


}
