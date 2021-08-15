package edu.miu.cs545.project.onlinestore.controller;

import edu.miu.cs545.project.onlinestore.domain.Payment;
import edu.miu.cs545.project.onlinestore.domain.Shipping;
import edu.miu.cs545.project.onlinestore.domain.ShoppingCart;
import edu.miu.cs545.project.onlinestore.domain.ShoppingCartLine;
import edu.miu.cs545.project.onlinestore.dto.ShoppingCartDTO;
import edu.miu.cs545.project.onlinestore.dto.ShoppingCartLineDTO;
import edu.miu.cs545.project.onlinestore.service.ShoppingCartService;
import edu.miu.cs545.project.onlinestore.service.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/shoppingcarts")
public class ShoppingCartController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    OrderController orderController;

    @GetMapping("/{cartId}")
    public ShoppingCartDTO getShoppingCart(@PathVariable Long cartId){
        Optional<ShoppingCart> shoppingCartcart = shoppingCartService.getShoppingCart(cartId);
        if(shoppingCartcart.isPresent()){
            return modelMapper.map(shoppingCartcart.get(), ShoppingCartDTO.class);
        }
        return null;
    }

    @GetMapping("/{cartId}/cartlines")
    public List<ShoppingCartLineDTO> getLinesFromShoppingCart(@PathVariable Long cartId){
        List<ShoppingCartLine> cartLines = shoppingCartService.getLinesByShoppingCart(cartId);
        return cartLines.stream()
                .map(line -> modelMapper.map(line, ShoppingCartLineDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping()
    public ShoppingCart createShoppingCart(@RequestBody ShoppingCart cart){
        return shoppingCartService.createShoppingCart(cart);
    }
       // updating  shipping cart line in shopping cart
    @PutMapping("/{cartId}/cartlines")
    public void updateLineInShoppingCart(@PathVariable Long cartId, @RequestBody ShoppingCartLine cartLine){
        shoppingCartService.updateLineInShoppingCart(cartId, cartLine);
    }
       // adding shopping cart  line to shopping cart
    @PostMapping("/{cartId}/cartlines")
    public void addLineToShoppingCart(@PathVariable Long cartId, @RequestBody ShoppingCartLine cartLine){
        shoppingCartService.addLineToShoppingCart(cartId, cartLine);
    }
       // updating the quantity in shopping cart
    @PutMapping("/{cartId}/cartlines/{lineId}")
    public void updateLineInShoppingCart(@PathVariable Long cartId, @PathVariable Long lineId, @RequestBody Integer newQuantity){
        shoppingCartService.updateQuantityInShoppingCartLine(cartId, lineId, newQuantity);
    }
      // removing line from shopping cart
    @DeleteMapping("/{cartId}/cartlines/{cartLineId}")
    public void removeLineToShoppingCart(@PathVariable Long cartId, @PathVariable Long cartLineId){
        shoppingCartService.removeLineFromShoppingCart(cartId, cartLineId);
    }

    @PostMapping("/{cartId}/createorder")
    public void createOrder(@PathVariable Long cartId, @RequestBody ShippingAndPayment shippingAndPayment) {
//        orderController.createOrderFromCart(cartId, shippingAndPayment.shipping, shippingAndPayment.payment);

        //simulation
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) authentication.getPrincipal();
        Shipping shipping = new Shipping(userdetails.getUser().getFirstName(),userdetails.getUser().getLastName(),userdetails.getUser().getPhoneNumber(),java.time.LocalDate.now(),"1000 N 4th Street","Fairfield","IOWA","USA","52557");
        Payment payment = new Payment(java.time.LocalDate.now(),new Double(10.0),"John","Credit");
        orderController.createOrderFromCart(cartId, shipping, payment);
        ///////////////////////
    }
}