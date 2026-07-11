package br.com.api.techchristian.series.service.finder;

import br.com.api.techchristian.series.database.models.Review;
import br.com.api.techchristian.series.database.repository.IReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReviewFinder {
    private final IReviewRepository reviewRepository;

    public boolean existsByUserIdAndMovieId(UUID userId, UUID movieId){
        return reviewRepository.existsByUserIdAndMovieId(userId, movieId);
    }

    public Double findAverage(UUID movieId){
        return reviewRepository.findAverageRating(movieId);
    }

    public Long countByMovieId(UUID movieId){
        return reviewRepository.countByMovieId(movieId);
    }

    public List<Review> listAll(){
        return reviewRepository.findAll();
    }
}
