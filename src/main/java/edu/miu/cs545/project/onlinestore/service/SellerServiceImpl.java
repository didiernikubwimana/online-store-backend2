package edu.miu.cs545.project.onlinestore.service;
import edu.miu.cs545.project.onlinestore.domain.Seller;
import edu.miu.cs545.project.onlinestore.domain.Product;
import edu.miu.cs545.project.onlinestore.repository.SellerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService{
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public List<Product> getProductsBySellerId(Long id) {
        return sellerRepository.getProductsBySellerId(id);
    }
    @Override
    public List<Seller> getAll() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller getSellerByID(Long id) {
        return sellerRepository.getSellerById(id);
    }


}