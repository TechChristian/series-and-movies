package br.com.api.techchristian.series.controller;

import br.com.api.techchristian.series.dto.ReviewDto;
import br.com.api.techchristian.series.openapi.ReviewOpenApi;
import br.com.api.techchristian.series.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/movies")
@RequiredArgsConstructor
public class ReviewController implements ReviewOpenApi {

    private final ReviewService reviewService;

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<ReviewDto.Response> addReview(@PathVariable UUID movieId, @RequestBody @Valid ReviewDto.Create create){

        ReviewDto.Response response = reviewService.createReview(movieId, create);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto.Response>> getAllReviews(){

        List<ReviewDto.Response> responseList = reviewService.getAllReviews();

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
