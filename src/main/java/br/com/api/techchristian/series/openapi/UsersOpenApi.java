package br.com.api.techchristian.series.openapi;

import br.com.api.techchristian.series.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
                                    schema = @Schema(implementation = UserDto.UserResponseDto.class)))
            }
    )
    public ResponseEntity<List<UserDto.UserResponseDto>> getAllUsers();
}
