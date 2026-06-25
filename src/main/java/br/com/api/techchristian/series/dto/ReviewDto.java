package br.com.api.techchristian.series.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewDto {
    public record create(
            @NotNull(message = "The number it cannot be null")
            @Size(min = 1, max = 5, message = "is required a note of 1 the 5.")
            Integer rating,

            @NotBlank(message = "A comment must be submitted.")
            @Size(min = 4, max = 150, message = "A comment between 4 and 150 characters is required. ")
            String comment
    ){}
    public record Response(
            Integer rating,

            String comment,

            String userName
    ){}
}