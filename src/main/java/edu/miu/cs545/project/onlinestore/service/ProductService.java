package edu.miu.cs545.project.onlinestore.service;
import java.util.Optional;
import java.util.List;
import edu.miu.cs545.project.onlinestore.domain.Product;
import edu.miu.cs545.project.onlinestore.domain.Review;


public interface ProductService {
    Optional<Product> getProductById(Long id);
    Boolean createProduct(Product product, Long id);
    void deleteProduct(Long id);
    List<Review> getApprovedReviewsByProductId(Long id);
    List<Product> getAll();
    Boolean updateProduct(Product product, Long id);
}
