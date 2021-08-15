package edu.miu.cs545.project.onlinestore.service;
import java.util.Optional;
import java.util.List;
import edu.miu.cs545.project.onlinestore.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import edu.miu.cs545.project.onlinestore.repository.ReviewRepository;
import org.springframework.stereotype.Service;
@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void createReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsNotApproved() {
        return reviewRepository.getReviewsNotApproved();
    }

    @Override
    public Boolean approveReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            review.get().setApproved(true);
            reviewRepository.save(review.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
}
