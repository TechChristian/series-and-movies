package br.com.api.techchristian.series.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UserDto {
    public record UserRegisterDto(
            @NotBlank(message = "name is required.")
            String name,

            @NotBlank(message = "email is required.")
            @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
            String email,

            @NotBlank(message = "Password is required.")
            @Size(min = 6, max = 12, message = "Your password must be between 6 and 12  digits.")
            String password
    ){}
    public record UserResponseDto(
            UUID id,

            String name,

            String email
    ){}

    public record UserLoginDto(
            @NotBlank(message = "email is required.")
            @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
            String email,

            @NotBlank(message = "Password is required.")
            String password
    ){}

}
