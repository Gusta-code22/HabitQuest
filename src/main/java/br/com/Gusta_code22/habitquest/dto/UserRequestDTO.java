package br.com.Gusta_code22.habitquest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotBlank(message = "Username cannot be blank")
        @Size(min = 3, max = 50, message = "The name must be between 3 and 50 characters long")
        String name,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "The format of the email is invalid")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Size(min = 2, message = "Password must be at least 2 characters")
        String password
) {
}
