package br.com.api.techchristian.series.service;

import br.com.api.techchristian.series.database.models.User;
import br.com.api.techchristian.series.database.repository.IUserRepository;
import br.com.api.techchristian.series.dto.UserDto;
import br.com.api.techchristian.series.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final IUserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDto.UserResponseDto> listAllUsers() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()) {throw new UsernameNotFoundException("User not found");}

        return UserMapper.toResponseDto(users);

    }

}
