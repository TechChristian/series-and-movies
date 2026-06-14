package br.com.api.techchristian.series.service;

import br.com.api.techchristian.series.database.enums.RoleName;
import br.com.api.techchristian.series.database.models.RolesEntity;
import br.com.api.techchristian.series.database.models.User;
import br.com.api.techchristian.series.database.repository.IRoleRepository;
import br.com.api.techchristian.series.database.repository.IUserRepository;
import br.com.api.techchristian.series.dto.TokenResponseDto;
import br.com.api.techchristian.series.dto.UserDto;
import br.com.api.techchristian.series.exception.EmailAlreadyException;
import br.com.api.techchristian.series.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
                .password(passwordEncoder.encode(registerDto.password()))
                .build());
    }

    @Transactional
    public TokenResponseDto login(UserDto.UserLoginDto userLoginDto){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.email(), userLoginDto.password()));
            String token = tokenProvider.generateToken(authentication);
            return new TokenResponseDto(token, expirationTime);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid email or password");
        }catch (Exception e){
            throw e;
        }
    }
}
