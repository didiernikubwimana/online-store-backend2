package edu.miu.cs545.project.onlinestore.service;

import java.util.List;

import edu.miu.cs545.project.onlinestore.domain.Product;
import edu.miu.cs545.project.onlinestore.domain.Seller;

public interface SellerService {
    List<Product> getProductsBySellerId(Long id);
    List<Seller> getAll();
    Seller getSellerByID(Long id);
}
