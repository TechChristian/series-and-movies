package br.com.api.techchristian.series.controller;

import br.com.api.techchristian.series.database.enums.ContentTypeEnum;
import br.com.api.techchristian.series.database.enums.GenreEnum;
import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.dto.MovieDto;
import br.com.api.techchristian.series.mappers.MovieMapper;
import br.com.api.techchristian.series.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDto.Response> createMovie(@Valid @RequestBody MovieDto.Create create) {
        Movie movie = movieService.addMovie(create);
        MovieDto.Response response = MovieMapper.toResponse(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{title}")
    public ResponseEntity<MovieDto.Response> getMovie(@PathVariable String title) {
        Movie movie = movieService.searchMovie(title);
        MovieDto.Response response = MovieMapper.toResponse(movie);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieDto.Response>> getGenre(@PathVariable GenreEnum genre) {
        List<Movie> movies = movieService.searchGenre(genre);
        return ResponseEntity.status(HttpStatus.OK).body(MovieMapper.toResponseList(movies));
    }

    @GetMapping("/type/{contentType}")
    public ResponseEntity<List<MovieDto.Response>> getContentType(@PathVariable ContentTypeEnum contentType) {
        List<Movie> responseContentType = movieService.searchContentType(contentType);
        return ResponseEntity.status(HttpStatus.OK).body(MovieMapper.toResponseList(responseContentType));
    }

    @GetMapping
    public ResponseEntity<List<MovieDto.Response>> getAllContents(){
        List<MovieDto.Response> responseList = movieService.listAllContents();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
