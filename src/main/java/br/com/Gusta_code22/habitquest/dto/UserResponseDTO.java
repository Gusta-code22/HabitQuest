package br.com.Gusta_code22.habitquest.dto;

public record UserResponseDTO(
        Long id, String name, String email, Integer xp, Integer level
) {
}
