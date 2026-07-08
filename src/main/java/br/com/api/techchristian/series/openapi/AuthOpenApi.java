package br.com.api.techchristian.series.openapi;

import br.com.api.techchristian.series.dto.UserDto;
import br.com.api.techchristian.series.handler.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthOpenApi {
    @Operation(
            summary = "Register a new user.",
            description = "A feature for register new user",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "create new user",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.UserResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "email already exists.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    public ResponseEntity<UserDto.UserResponseDto> register (@Valid @RequestBody UserDto.UserRegisterDto registerDto);
}
