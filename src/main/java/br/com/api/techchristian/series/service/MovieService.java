package br.com.api.techchristian.series.service;

import br.com.api.techchristian.series.database.enums.ContentTypeEnum;
import br.com.api.techchristian.series.database.enums.GenreEnum;
import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.database.repository.IMovieRepository;
import br.com.api.techchristian.series.dto.MovieDto;
import br.com.api.techchristian.series.exception.GenreNotFoundException;
import br.com.api.techchristian.series.exception.MovieAlreadyExistsException;
import br.com.api.techchristian.series.exception.MovieNotFoundException;
import br.com.api.techchristian.series.mappers.MovieMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final IMovieRepository movieRepository;

    @Transactional
    public Movie addMovie(MovieDto.Create movieInfos) {

        Movie movieRequestToEntity = MovieMapper.toEntity(movieInfos);

        boolean existsTitle = movieRepository.existsByTitle(movieRequestToEntity.getTitle());

        if (existsTitle) {throw new MovieAlreadyExistsException("Movie already exists.");}

        return movieRepository.save(movieRequestToEntity);
    }

    @Transactional(readOnly = true)
    public Movie searchTitle(String title) {
       return movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found."));
    }

    @Transactional(readOnly = true)
    public List<Movie> searchGenre(GenreEnum genre) {
        boolean existsGenre = movieRepository.existsByGenre(genre);

        if(!existsGenre) {throw new GenreNotFoundException("Genre not found.");}

        return movieRepository.findByGenre(genre);

    }

    @Transactional(readOnly = true)
    public List<Movie> searchContentType(ContentTypeEnum contentType) {
        return movieRepository.findByContentType(contentType);
    }

    @Transactional(readOnly = true)
    public List<MovieDto.Response> listAllContents(){

        List<Movie> movies = movieRepository.findAll();

        if(movies.isEmpty()) {throw new MovieNotFoundException("No movies or series found.");}

        return MovieMapper.toResponseList(movies);
    }
}