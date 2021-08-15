package edu.miu.cs545.project.onlinestore.service;
import java.util.Optional;
import java.util.List;

import edu.miu.cs545.project.onlinestore.domain.Review;

public interface ReviewService {
    List<Review> getReviewsNotApproved();
    Optional<Review> getReviewById(Long id);
    void createReview(Review review);
    Boolean approveReview(Long id);
}
