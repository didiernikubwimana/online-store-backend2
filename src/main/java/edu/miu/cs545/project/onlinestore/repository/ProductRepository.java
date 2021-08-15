package edu.miu.cs545.project.onlinestore.repository;

import edu.miu.cs545.project.onlinestore.domain.Product;
import edu.miu.cs545.project.onlinestore.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    public Product save(Product product);
    public Optional<Product> findById(Long productId);
    public List<Product> findAll();
    public void deleteById(Long productId);
    @Query(value = "SELECT p.reviews FROM Product p WHERE p.id = :productId")
    public List<Review> findReviewsByProductId(@Param("productId") Long productId);

}