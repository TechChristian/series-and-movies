package br.com.api.techchristian.series.service;

import br.com.api.techchristian.series.database.enums.ContentTypeEnum;
import br.com.api.techchristian.series.database.enums.GenreEnum;
import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.database.repository.IMovieRepository;
import br.com.api.techchristian.series.database.repository.IReviewRepository;
import br.com.api.techchristian.series.dto.MovieDto;
import br.com.api.techchristian.series.exception.GenreNotFoundException;
import br.com.api.techchristian.series.exception.MovieAlreadyExistsException;
import br.com.api.techchristian.series.exception.MovieNotFoundException;
import br.com.api.techchristian.series.mappers.MovieMapper;
import br.com.api.techchristian.series.openapi.MoviesOpenApi;
import br.com.api.techchristian.series.service.finder.MovieFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {
    private final IMovieRepository movieRepository;
    private final IReviewRepository reviewRepository;
    private final MovieFinder movieFinder;
    @Transactional
    public Movie addMovie(MovieDto.Create movieInfos) {

        Movie movieRequestToEntity = MovieMapper.toEntity(movieInfos);

        boolean existsTitle = movieRepository.existsByTitle(movieRequestToEntity.getTitle());

        if (existsTitle) {
            throw new MovieAlreadyExistsException("Movie already exists.");
        }

        return movieRepository.save(movieRequestToEntity);
    }

    @Transactional(readOnly = true)
    public Movie searchTitle(String title) {
       return movieFinder.byTitle(title);
    }

    @Transactional(readOnly = true)
    public List<Movie> searchGenre(GenreEnum genre) {
        boolean existsGenre = movieRepository.existsByGenre(genre);

        if (!existsGenre) {
            throw new GenreNotFoundException("Genre not found.");
        }

        return movieRepository.findByGenre(genre);

    }

    @Transactional(readOnly = true)
    public List<Movie> searchContentType(ContentTypeEnum contentType) {
        return movieRepository.findByContentType(contentType);
    }

    @Transactional(readOnly = true)
    @Cacheable("movies")
    public List<MovieDto.Response> listAllContents() {
        log.info("retrieving all contents... ");

        List<Movie> movies = movieRepository.findAll();

        if (movies.isEmpty()) {
            throw new MovieNotFoundException("No movies or series found.");
        }

        return MovieMapper.toResponseList(movies);
    }

    @Transactional
    public Movie updateFieldsMovie(UUID id, MovieDto.Update movieFieldsUpdate) {
        Movie movieExists = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie not found."));

        if (movieFieldsUpdate.title() != null) {
            movieExists.setTitle(movieFieldsUpdate.title());
        }

        if (movieFieldsUpdate.description() != null) {
            movieExists.setDescription(movieFieldsUpdate.description());
        }

        if (movieFieldsUpdate.genre() != null) {
            movieExists.setGenre(movieFieldsUpdate.genre());
        }

        return movieExists;
    }

    @Transactional
    public void deleteMovie(UUID id) {

        reviewRepository.deleteByMovieId(id);

        movieFinder.byId(id);

        movieRepository.deleteById(id);
    }
}