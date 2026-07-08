package br.com.api.techchristian.series.controller;

import br.com.api.techchristian.series.database.models.User;
import br.com.api.techchristian.series.dto.TokenResponseDto;
import br.com.api.techchristian.series.dto.UserDto;
import br.com.api.techchristian.series.mappers.UserMapper;
import br.com.api.techchristian.series.openapi.AuthOpenApi;
import br.com.api.techchristian.series.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/auth")
@Slf4j
public class AuthController implements AuthOpenApi {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserDto.UserResponseDto> register (@Valid @RequestBody UserDto.UserRegisterDto registerDto) {
        log.info("Create register user: name - {}, email - {}", registerDto.name(), registerDto.email());

        User user =  authenticationService.register(registerDto);
        UserDto.UserResponseDto response =
                UserMapper.toResponseDto(user);
        log.info("Register user: name - {}, email - {}", response.name(), response.email());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login (@Valid @RequestBody UserDto.UserLoginDto loginDto){
        TokenResponseDto token = authenticationService.login(loginDto);
        return ResponseEntity.ok(token);
    }
}
