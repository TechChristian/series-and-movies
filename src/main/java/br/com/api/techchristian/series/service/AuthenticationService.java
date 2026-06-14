package br.com.api.techchristian.series.service;

import br.com.api.techchristian.series.database.enums.RoleName;
import br.com.api.techchristian.series.database.models.RolesEntity;
import br.com.api.techchristian.series.database.models.User;
import br.com.api.techchristian.series.database.repository.IRoleRepository;
import br.com.api.techchristian.series.database.repository.IUserRepository;
import br.com.api.techchristian.series.dto.UserDto;
import br.com.api.techchristian.series.exception.EmailAlreadyException;
import br.com.api.techchristian.series.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IRoleRepository  roleRepository;
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    @Value("${jwt.expiration}")
    private Long expirationTime;

    @Transactional
    public User register(UserDto.UserRegisterDto registerDto){
        User user = repository.findByEmail(registerDto.email()).orElse(null);

        if(user != null){
            throw new EmailAlreadyException("email already exists");
        }

        RolesEntity role = roleRepository.findByName(RoleName.ROLE_USER.name())
                .orElseGet(() -> roleRepository.save(RolesEntity.builder()
                        .name(RoleName.ROLE_USER.name()).build()));

        return repository.save(User.builder()
                .name(registerDto.name())
                .email(registerDto.email())
                .roles(Set.of(role))
                .password(registerDto.password())
                .build());
    }
}
