package br.com.api.techchristian.series.controller;

import br.com.api.techchristian.series.dto.MessageResponseDto;
import br.com.api.techchristian.series.dto.UserDto;
import br.com.api.techchristian.series.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> userDelete(@PathVariable UUID id){
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
