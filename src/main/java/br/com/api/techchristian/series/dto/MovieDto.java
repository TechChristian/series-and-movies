package br.com.api.techchristian.series.dto;

import br.com.api.techchristian.series.database.enums.ContentTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class MovieDto {
    public record create(
            @NotBlank(message = "title is required.")
            @Size(min = 2, max = 255, message = "title must be between 2 and 255 characters.")
            String title,

            @NotBlank(message = "description is required.")
            @Size(min = 10, message = "description must be at least 10 characters.")
            String description,

            @NotBlank(message = "genre is required.")
            @Size(min = 4, max = 50, message = "genre must be between 4 and 50 characters.")
            String genre,

            @NotBlank(message = "type is required.")
            ContentTypeEnum type,

            @NotBlank(message = "releaseYear is required.")
            Integer releaseYear
    ){}

    public record response(
    String title,
    String description,
    String genre,
    ContentTypeEnum type,
    Integer releaseYear,
    LocalDateTime createAt
    ){}
}
