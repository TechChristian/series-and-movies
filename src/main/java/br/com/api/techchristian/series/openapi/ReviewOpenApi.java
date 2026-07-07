package br.com.api.techchristian.series.openapi;

import br.com.api.techchristian.series.dto.ReviewDto;
import br.com.api.techchristian.series.handler.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface ReviewOpenApi {
    @Operation(
            summary = "Create Review",
            description = "A feature for add new review in movies or series.",
            responses = {
                    @ApiResponse(
                        responseCode = "201",
                            description = "create review",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewDto.Response.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Movie not found.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "409",
                            description = "You have already reviewed this movie.",
                            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    public ResponseEntity<ReviewDto.Response> addReview(@PathVariable UUID movieId, @RequestBody @Valid ReviewDto.Create create);
}
