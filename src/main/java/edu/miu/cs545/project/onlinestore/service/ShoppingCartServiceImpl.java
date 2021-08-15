package edu.miu.cs545.project.onlinestore.service;
import java.util.Optional;
import java.util.List;
import edu.miu.cs545.project.onlinestore.repository.ShoppingCartRepository;
import edu.miu.cs545.project.onlinestore.domain.ShoppingCart;
import edu.miu.cs545.project.onlinestore.repository.ShoppingCartLineRepository;
import edu.miu.cs545.project.onlinestore.domain.ShoppingCartLine;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Autowired
    private ShoppingCartLineRepository shoppingCartLineRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Override
    public ShoppingCart createShoppingCart(ShoppingCart cart) {
        return shoppingCartRepository.save(cart);
    }

    @Override
    public void removeLineFromShoppingCart(Long cartId, Long cartLineId) {
        Optional<ShoppingCart> cart =  shoppingCartRepository.findById(cartId);
        if(cart.isPresent()){
            ShoppingCart foundCart = cart.get();
            Optional<ShoppingCartLine> cartLine = foundCart.getCartLines().stream()
                    .filter(line-> line.getId() == cartLineId)
                    .findFirst();
            if(cartLine.isPresent()){
                foundCart.setTotalMoney(foundCart.getTotalMoney() - cartLine.get().getLineTotal());
                shoppingCartRepository.save(foundCart);
                shoppingCartLineRepository.deleteById(cartLineId);
            }
        }
    }

    @Override
    public void addLineToShoppingCart(Long id, ShoppingCartLine cartLine) {
        Optional<ShoppingCart> cart =  shoppingCartRepository.findById(id);
        if(cart.isPresent()){
            ShoppingCart foundCart = cart.get();
            foundCart.setTotalMoney(foundCart.getTotalMoney() + cartLine.getLineTotal());
            cartLine.setCart(foundCart);
            shoppingCartRepository.save(foundCart);
            shoppingCartLineRepository.save(cartLine);
        }
    }

    @Override
    public List<ShoppingCartLine> getLinesByShoppingCart(Long id) {
        return shoppingCartRepository.getLinesByShoppingCard(id);
    }

    @Override
    public void updateLineInShoppingCart(Long cartId, ShoppingCartLine newCartLine) {
        Optional<ShoppingCart> cart =  shoppingCartRepository.findById(cartId);
        Optional<ShoppingCartLine> cartLine =  shoppingCartLineRepository.findById(newCartLine.getId());
        if(cartLine.isPresent() && cart.isPresent()){
            ShoppingCart foundCart = cart.get();
            ShoppingCartLine foundCartLine = cartLine.get();
            foundCart.setTotalMoney(foundCart.getTotalMoney()  + newCartLine.getLineTotal() - foundCartLine.getLineTotal());
            newCartLine.setCart(foundCart);
            shoppingCartLineRepository.save(newCartLine);
            shoppingCartRepository.save(foundCart);
        }
    }

    @Override
    public Optional<ShoppingCart> getShoppingCart(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public void updateQuantityInShoppingCartLine(Long cartId, Long lineId, Integer newQuantity) {
        Optional<ShoppingCart> cart =  shoppingCartRepository.findById(cartId);
        Optional<ShoppingCartLine> cartLine =  shoppingCartLineRepository.findById(lineId);
        if(cart.isPresent() && cartLine.isPresent()){
            ShoppingCart foundCart = cart.get();
            ShoppingCartLine foundCartLine = cartLine.get();
            Double oldLineTotal =  foundCartLine.getLineTotal();
            Double newLineTotal = foundCartLine.getPrice()*newQuantity;
            foundCartLine.setQuantity(newQuantity);
            foundCartLine.setLineTotal(newLineTotal);
            foundCart.setTotalMoney(foundCart.getTotalMoney() - oldLineTotal + newLineTotal);
            shoppingCartLineRepository.save(foundCartLine);
            shoppingCartRepository.save(foundCart);
        }
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartByBuyerNotCompleted(Long id) {
        return shoppingCartRepository.getShoppingCartByBuyerNotCompleted(id);
    }
}

