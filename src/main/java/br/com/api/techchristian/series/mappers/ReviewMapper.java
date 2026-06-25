package br.com.api.techchristian.series.mappers;


import br.com.api.techchristian.series.database.models.Movie;
import br.com.api.techchristian.series.database.models.Review;
import br.com.api.techchristian.series.database.models.User;
import br.com.api.techchristian.series.dto.ReviewDto;

public class ReviewMapper {
    public static Review toEntity(ReviewDto.create create, User user, Movie movie) {
        Review review = new Review();

        return Review.builder()
                .rating(create.rating())
                .comment(create.comment())
                .user(user)
                .movie(movie)
                .build();
    }
    public static ReviewDto.Response toResponse(Review review) {
        return new ReviewDto.Response(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getUser().getName(),
                review.getCreatedAt()
        );
    }
}
