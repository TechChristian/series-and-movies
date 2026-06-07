package br.com.api.techchristian.series.service;

import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.database.repository.IMovieRepository;
import br.com.api.techchristian.series.dto.MovieDto;
import br.com.api.techchristian.series.exception.MovieAlreadyExistsException;
import br.com.api.techchristian.series.mappers.MovieMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Movie searchMovie(String title) {
       return movieRepository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found."));
    }
}