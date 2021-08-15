package edu.miu.cs545.project.onlinestore.service;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import edu.miu.cs545.project.onlinestore.domain.Review;
import edu.miu.cs545.project.onlinestore.domain.Product;
import edu.miu.cs545.project.onlinestore.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import edu.miu.cs545.project.onlinestore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import edu.miu.cs545.project.onlinestore.domain.Seller;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public Boolean createProduct(Product product, Long id) {
        try {
            Seller seller = sellerRepository.getSellerByUserId(id);
            if(seller != null){
                product.setSeller(seller);
                productRepository.save(product);
                return true;
            }
            return false;
        }catch (Exception ex){
            return false;
        }
    }
    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Review> getApprovedReviewsByProductId(Long id) {
        List<Review> reviews = productRepository.findReviewsByProductId(id);
        return reviews.stream().filter(res->res.isApproved()).collect(Collectors.toList());
    }

    @Override
    public Boolean updateProduct(Product product, Long id) {
        try {
            Seller seller = sellerRepository.getSellerByUserId(id);
            if(seller != null){
                product.setSeller(seller);
                productRepository.save(product);
                return true;
            }
            return false;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
