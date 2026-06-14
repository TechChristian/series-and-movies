package br.com.api.techchristian.series.mappers;

import br.com.api.techchristian.series.database.models.User;
import br.com.api.techchristian.series.dto.UserDto;

public class UserMapper {

    public static UserDto.UserLoginDto toResponseDto(User user){
        return new  UserDto.UserLoginDto(
                user.getEmail(),
                user.getName()
        );
    }
}
