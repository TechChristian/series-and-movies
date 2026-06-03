package br.com.api.techchristian.series.mappers;

import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.dto.MovieDto;

public class MovieMapper {
    public static Movie toEntity(MovieDto.Create create) {
        Movie movie = new Movie();

        movie.setTitle(create.title());
        movie.setDescription(create.description());
        movie.setGenre(create.genre());
        movie.setContentType(create.type());
        movie.setReleaseYear(create.releaseYear());
        return movie;
    }

    public static MovieDto.Response toResponse(Movie movie) {
        return new MovieDto.Response(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getGenre(),
                movie.getContentType(),
                movie.getReleaseYear(),
                movie.getCreatedAt()
        );
    }
}
