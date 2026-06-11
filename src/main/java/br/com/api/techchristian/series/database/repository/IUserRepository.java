package br.com.api.techchristian.series.database.repository;

import br.com.api.techchristian.series.database.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
