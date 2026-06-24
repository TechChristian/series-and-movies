package br.com.api.techchristian.series.controller;

import br.com.api.techchristian.series.database.enums.ContentTypeEnum;
import br.com.api.techchristian.series.database.enums.GenreEnum;
import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.dto.MessageResponseDto;
import br.com.api.techchristian.series.dto.MovieDto;
import br.com.api.techchristian.series.mappers.MovieMapper;
import br.com.api.techchristian.series.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/movies")
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MovieDto.Response> createMovie(@Valid @RequestBody MovieDto.Create create) {

        log.info("Movie creation requested with these infos: title - {}, description - {}, genre - {}, type - {}, release year - {}",
                create.title(),
                create.description(),
                create.genre(),
                create.type(),
                create.releaseYear());

        Movie movie = movieService.addMovie(create);

        MovieDto.Response response = MovieMapper.toResponse(movie);
        log.info(
                "Movie created successfully. id={}, title={}",
                movie.getId(),
                movie.getTitle());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{title}")
    public ResponseEntity<MovieDto.Response> getTitle(@PathVariable String title) {
        Movie movie = movieService.searchTitle(title);
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

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<MessageResponseDto> updateMovieFields(@PathVariable UUID id, @Valid @RequestBody MovieDto.Update update){
        movieService.updateFieldsMovie(id, update);

        return ResponseEntity.ok(
                new MessageResponseDto(
                        "Movie info has been successfully updated."
                )
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id){
        movieService.deleteMovie(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
