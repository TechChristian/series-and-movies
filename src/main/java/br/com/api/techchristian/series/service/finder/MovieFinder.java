package br.com.api.techchristian.series.service.finder;

import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.database.repository.IMovieRepository;
import br.com.api.techchristian.series.exception.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MovieFinder {
    private final IMovieRepository movieRepository;

    public void byId(UUID id) {
        movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found."));
    }

    public Movie byTitle(String title) {
     return movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found."));
    }
}

