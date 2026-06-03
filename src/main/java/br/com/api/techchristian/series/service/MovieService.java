package br.com.api.techchristian.series.service;

import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.database.repository.IMovieRepository;
import br.com.api.techchristian.series.dto.MovieDto;
import br.com.api.techchristian.series.mappers.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final IMovieRepository movieRepository;

    @Transactional
    public Movie save(MovieDto.Create dto) {

        Movie movie = movieRepository.findByTitle(dto.title()).orElse(null);

        if (movie != null) {
            throw new IllegalArgumentException("Movie already exists");
        }

        Movie newMovie = MovieMapper.toEntity(dto);
        return movieRepository.save(newMovie);
    }
}
