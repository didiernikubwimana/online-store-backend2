package edu.miu.cs545.project.onlinestore.service;

import java.util.Optional;
import java.util.List;
import edu.miu.cs545.project.onlinestore.domain.ShoppingCart;
import edu.miu.cs545.project.onlinestore.domain.ShoppingCartLine;
public interface ShoppingCartService {
    void addLineToShoppingCart(Long cartId, ShoppingCartLine cartLine);
    List<ShoppingCartLine> getLinesByShoppingCart(Long id);
    ShoppingCart createShoppingCart(ShoppingCart cart);
    void updateLineInShoppingCart(Long cartId, ShoppingCartLine newCartLine);
    void updateQuantityInShoppingCartLine(Long cartId,Long lineId,Integer newQuantity);
    Optional<ShoppingCart> getShoppingCart(Long id);
    void removeLineFromShoppingCart(Long cartId, Long id);
    Optional<ShoppingCart> getShoppingCartByBuyerNotCompleted(Long id);
}
