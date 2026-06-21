package br.com.api.techchristian.series.controller;

import br.com.api.techchristian.series.dto.UserDto;
import br.com.api.techchristian.series.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/users")

public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto.UserResponseDto>> getAllUsers(){
        List<UserDto.UserResponseDto> responseList = userService.listAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

}
