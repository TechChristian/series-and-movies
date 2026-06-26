package br.com.api.techchristian.series.dto;

import br.com.api.techchristian.series.database.enums.ContentTypeEnum;
import br.com.api.techchristian.series.database.enums.GenreEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public class MovieDto {
    public record Create(
            @NotBlank(message = "title is required.")
            @Size(min = 2, max = 255, message = "title must be between 2 and 255 characters.")
            String title,

            @NotBlank(message = "description is required.")
            @Size(min = 10, message = "description must be at least 10 characters.")
            String description,

            @NotNull(message = "genre is required.")
            GenreEnum genre,

            @NotNull(message = "type is required.")
            ContentTypeEnum type,

            @NotNull(message = "releaseYear is required.")
            Integer releaseYear
    ) {
    }

    public record Update(
            String title,
            String description,
            GenreEnum genre
    ){}

    public record Response(
            UUID id,
            String title,
            String description,
            GenreEnum genre,
            ContentTypeEnum type,
            Integer releaseYear,
            Double rating,
            LocalDateTime createAt
    ) {
    }
}
