package br.com.api.techchristian.series.mappers;

import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.dto.MovieDto;

import java.util.List;

public class MovieMapper {
    public static Movie toEntity(MovieDto.Create dto) {
        Movie movie = new Movie();

        return Movie.builder()
                        .title(dto.title())
                        .description(dto.description())
                        .genre(dto.genre())
                        .contentType(dto.type())
                        .releaseYear(dto.releaseYear())
                        .build();
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

    public static List<MovieDto.Response> toResponseList(List<Movie> movies) {
        return
                movies
                        .stream()
                        .map(MovieMapper::toResponse)
                        .toList();
    }
}
