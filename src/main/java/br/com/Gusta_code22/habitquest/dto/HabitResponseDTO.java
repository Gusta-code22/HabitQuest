package br.com.Gusta_code22.habitquest.dto;

import java.time.LocalDateTime;

public record HabitResponseDTO(
        Long id, String name, String description, Integer streak, LocalDateTime creationDate, Long userId
) {
}
