package edu.miu.cs545.project.onlinestore.repository;

import java.util.Optional;
import java.util.List;

import edu.miu.cs545.project.onlinestore.domain.ShoppingCartLine;
import edu.miu.cs545.project.onlinestore.domain.ShoppingCart;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart,Long> {
    @Query(value = "SELECT sh FROM ShoppingCart sh WHERE sh.buyer.id = :buyerId and sh.completed <> 1")
    public Optional<ShoppingCart> getShoppingCartByBuyerNotCompleted(@Param("buyerId") Long cartId);
    public Optional<ShoppingCart> findById(Long cartId);
    public ShoppingCart save(ShoppingCart shoppingCart);
    @Query(value = "SELECT sh.cartLines FROM ShoppingCart sh WHERE sh.id = :cartId")
    public List<ShoppingCartLine> getLinesByShoppingCard(@Param("cartId") Long cartId);
}
