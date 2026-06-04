package br.com.api.techchristian.series.controller;

import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.dto.MovieDto;
import br.com.api.techchristian.series.mappers.MovieMapper;
import br.com.api.techchristian.series.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDto.Response> createMovie(@Valid @RequestBody MovieDto.Create create) {
        Movie movie = movieService.save(create);
        MovieDto.Response response = MovieMapper.toResponse(movie);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
