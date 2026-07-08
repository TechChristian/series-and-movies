package br.com.api.techchristian.series.openapi;

import br.com.api.techchristian.series.dto.MovieDto;
import br.com.api.techchristian.series.handler.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface MoviesOpenApi {
    @Operation(
            summary = "create movies or series",
            description = "A feature for create new movie or series",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "create successful",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDto.Response.class))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Movie already exists.",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    public ResponseEntity<MovieDto.Response> createMovie(@Valid @RequestBody MovieDto.Create create);
}
