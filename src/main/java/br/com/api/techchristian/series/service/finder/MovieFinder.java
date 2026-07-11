package br.com.api.techchristian.series.service.finder;

import br.com.api.techchristian.series.database.enums.ContentTypeEnum;
import br.com.api.techchristian.series.database.enums.GenreEnum;
import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.database.repository.IMovieRepository;
import br.com.api.techchristian.series.exception.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MovieFinder {
    private final IMovieRepository movieRepository;

    public Movie byId(UUID id) {
      return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found."));
    }

    public Movie byTitle(String title) {
     return movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found."));
    }

    public List<Movie> searchType(ContentTypeEnum contentType){
        return movieRepository.findByContentType(contentType);
    }

    public boolean byGenreExists(GenreEnum genre){
        return movieRepository.existsByGenre(genre);
    }

    public List<Movie> byGenre(GenreEnum genre){
        return movieRepository.findByGenre(genre);
    }

    public List<Movie> listAll(){
        return movieRepository.findAll();
    }
}

