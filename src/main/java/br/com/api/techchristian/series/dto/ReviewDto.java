package br.com.api.techchristian.series.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewDto {
    public record Create(
            @NotNull(message = "The number it cannot be null")
            @Min(value = 1, message = "The minimum rating is 1.")
            @Max(value = 5, message = "The maximum rating is 5.")
            Integer rating,

            @NotBlank(message = "A comment must be submitted.")
            @Size(min = 4, max = 150, message = "A comment between 4 and 150 characters is required. ")
            String comment
    ){}
    public record Response(
            UUID id,

            Integer rating,

            String comment,

            String userName,

            LocalDateTime createdAt
    ){ }
}