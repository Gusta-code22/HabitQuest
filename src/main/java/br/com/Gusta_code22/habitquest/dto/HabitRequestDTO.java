package br.com.Gusta_code22.habitquest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HabitRequestDTO(

        @NotBlank(message = "The name of the habit is mandatory and cannot be blank")
        @Size(min = 3, max = 50, message = "The habit name must be between 3 and 50 characters long")
        String name,

        @Size(max = 255, message = "Description cannot exceed 255 characters")
        String description,

        @NotNull(message = "User ID is required to link habit to player")
        Long userId
) {
}