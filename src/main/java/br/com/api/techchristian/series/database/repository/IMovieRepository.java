package br.com.api.techchristian.series.database.repository;

import br.com.api.techchristian.series.database.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IMovieRepository extends JpaRepository<Movie, UUID> {
    Optional<Movie> findByTitle(String title);
}
