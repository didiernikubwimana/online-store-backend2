package edu.miu.cs545.project.onlinestore.controller;

import edu.miu.cs545.project.onlinestore.domain.Review;
import edu.miu.cs545.project.onlinestore.dto.ReviewDTO;
import edu.miu.cs545.project.onlinestore.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping()
    public void createReview(@RequestBody Review review){
        reviewService.createReview(review);
    }

    @GetMapping("/{reviewId}")
    public @ResponseBody ReviewDTO getReviewById(@PathVariable Long reviewId){
        Optional<Review> reviewOptional = reviewService.getReviewById(reviewId);
        if(reviewOptional.isPresent()){
            return modelMapper.map(reviewOptional.get(), ReviewDTO.class);
        }
        return null;
    }

    @GetMapping("/notapproved")
    public @ResponseBody List<ReviewDTO> getReviewsNotApproved(){
        List<Review> reviews = reviewService.getReviewsNotApproved();
        return reviews.stream()
                .map(proRev -> modelMapper.map(proRev,ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{reviewId}/approve")
    public Boolean approveReview(@PathVariable Long reviewId){
        return reviewService.approveReview(reviewId);
    }

}
