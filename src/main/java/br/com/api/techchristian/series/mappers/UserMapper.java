package br.com.api.techchristian.series.mappers;

import br.com.api.techchristian.series.database.models.User;
import br.com.api.techchristian.series.dto.UserDto;

import java.util.List;

public class UserMapper {

    public static UserDto.UserResponseDto toResponseDto(User user){
        return new UserDto.UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static List<UserDto.UserResponseDto> toResponseDto(List<User> users){
        return
                users
                        .stream()
                        .map(UserMapper::toResponseDto)
                        .toList();
    }
}
