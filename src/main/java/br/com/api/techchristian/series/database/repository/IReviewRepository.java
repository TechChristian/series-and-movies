package br.com.api.techchristian.series.database.repository;

import br.com.api.techchristian.series.database.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface IReviewRepository extends JpaRepository<Review, UUID> {
    boolean existsByUserIdAndMovieId(UUID id, UUID movieId);

    @Query("""
        SELECT COALESCE(AVG(r.rating), 0)
        FROM Review r
        WHERE r.movie.id = :movieId
"""
    )
    Double findAverageRating(@Param("movieId") UUID movieId);

    Long countByMovieId(UUID id);
}
