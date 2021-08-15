package edu.miu.cs545.project.onlinestore.repository;

import edu.miu.cs545.project.onlinestore.domain.Seller;
import edu.miu.cs545.project.onlinestore.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface SellerRepository extends CrudRepository<Seller,Long> {
    public Seller getSellerByUserId(@Param("userId") Long id);
    public Seller getSellerById(Long id);
    public List<Seller> findAll();
    @Query(value = "SELECT s.products FROM Seller s WHERE s.id = :id")
    List<Product> getProductsBySellerId(@Param("id") Long id);
}