package br.com.api.techchristian.series.service;

import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.database.models.Review;
import br.com.api.techchristian.series.database.models.User;
import br.com.api.techchristian.series.database.repository.IMovieRepository;
import br.com.api.techchristian.series.database.repository.IReviewRepository;
import br.com.api.techchristian.series.dto.ReviewDto;
import br.com.api.techchristian.series.exception.MovieNotFoundException;
import br.com.api.techchristian.series.exception.ReviewAlreadyExistsException;
import br.com.api.techchristian.series.exception.ReviewNotFoundException;
import br.com.api.techchristian.series.mappers.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final IReviewRepository reviewRepository;
    private final IMovieRepository movieRepository;

    @Transactional
    public ReviewDto.Response createReview(UUID movieId, ReviewDto.Create create){
        // * return user
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        User user = (User) authentication.getPrincipal();

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        boolean existsByUserIdAndMovieId = reviewRepository.existsByUserIdAndMovieId(user.getId(), movie.getId());

        if(existsByUserIdAndMovieId){throw new ReviewAlreadyExistsException("You have already reviewed this movie.");}

        Review review = ReviewMapper.toEntity(create, user, movie);

        reviewRepository.save(review);

        updateMovieRating(movie);

        return ReviewMapper.toResponse(review);

    }

    private void updateMovieRating(Movie movie) {
        Double average = reviewRepository.findAverageRating(movie.getId());
        Long total = reviewRepository.countByMovieId(movie.getId());

        if(average == null){
            average = 0.0;
        }

        movie.setAverageRating(average);
        movie.setTotalReviews(total.intValue());

        movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public List<ReviewDto.Response> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();

        if(reviews.isEmpty()){
            throw new ReviewNotFoundException("Reviews not found.");
        }

        return ReviewMapper.toResponseList(reviews);


    }
}
