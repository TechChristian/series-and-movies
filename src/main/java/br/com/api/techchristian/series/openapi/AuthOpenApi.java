package br.com.api.techchristian.series.openapi;

import br.com.api.techchristian.series.dto.TokenResponseDto;
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
            description = "A feature for register new user.",
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

    @Operation(
            summary = "Login of user.",
            description = "A feature for login of user.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login successful.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TokenResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "invalid email or password.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    public ResponseEntity<TokenResponseDto> login (@Valid @RequestBody UserDto.UserLoginDto loginDto);

}
