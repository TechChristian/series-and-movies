package br.com.api.techchristian.series.openapi;

import br.com.api.techchristian.series.database.enums.ContentTypeEnum;
import br.com.api.techchristian.series.database.enums.GenreEnum;
import br.com.api.techchristian.series.dto.MessageResponseDto;
import br.com.api.techchristian.series.dto.MovieDto;
import br.com.api.techchristian.series.handler.ErrorMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;
public interface MoviesOpenApi {
    @Operation(
            summary = "Create a movie or series",
            description = "Creates a new movie or series in the catalog.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Movie created successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MovieDto.Response.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access denied.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Movie already exists.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    ResponseEntity<MovieDto.Response> createMovie(@Valid @RequestBody MovieDto.Create create);


    @Operation(
            summary = "Find movie by title",
            description = "Returns a movie or series based on its title.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Movie found successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MovieDto.Response.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Movie not found.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    ResponseEntity<MovieDto.Response> getTitle(@PathVariable String title);


    @Operation(
            summary = "Find movies by genre",
            description = "Returns all movies and series belonging to the specified genre.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Movies found successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MovieDto.Response.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid genre.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Genre not found.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    ResponseEntity<List<MovieDto.Response>> getGenre(@PathVariable GenreEnum genre);


    @Operation(
            summary = "Find movies by content type",
            description = "Returns all contents filtered by movie or series.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contents retrieved successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MovieDto.Response.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid content type.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    ResponseEntity<List<MovieDto.Response>> getContentType(@PathVariable ContentTypeEnum contentType);


    @Operation(
            summary = "List all movies and series",
            description = "Returns all movies and series available in the catalog.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Contents retrieved successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MovieDto.Response.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No movies or series found.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    ResponseEntity<List<MovieDto.Response>> getAllContents();


    @Operation(
            summary = "Update movie information",
            description = "Updates one or more fields of an existing movie or series.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Movie updated successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MessageResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access denied.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Movie not found.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    ResponseEntity<MessageResponseDto> updateMovieFields(
            @PathVariable UUID id,
            @Valid @RequestBody MovieDto.Update update
    );


    @Operation(
            summary = "Delete a movie or series",
            description = "Deletes a movie or series from the catalog.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Movie deleted successfully."
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access denied.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Movie not found.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    ResponseEntity<Void> deleteMovie(@PathVariable UUID id);

}