package br.com.api.techchristian.series.openapi;

import br.com.api.techchristian.series.dto.UserDto;
import br.com.api.techchristian.series.handler.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface UsersOpenApi {
    @Operation(
            summary = "List all users",
            description = "A feature for list all users",
            responses = {
                    @ApiResponse(responseCode = "200",
                    description = "user list with successfully",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.UserResponseDto.class))),

                    @ApiResponse(responseCode = "404",
                            description = "Users not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    public ResponseEntity<List<UserDto.UserResponseDto>> getAllUsers();

    @Operation(
            summary = "Delete user",
            description = "A feature for Delete user for the Id.",
            responses = {
                    @ApiResponse(responseCode = "204",
                    content = @Content(mediaType = "application/json")),

                    @ApiResponse(responseCode = "404",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    public ResponseEntity<Void> userDelete(@PathVariable UUID id);

}
