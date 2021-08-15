package edu.miu.cs545.project.onlinestore.controller;

import edu.miu.cs545.project.onlinestore.domain.ShoppingCart;
import edu.miu.cs545.project.onlinestore.dto.ShoppingCartDTO;
import edu.miu.cs545.project.onlinestore.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/buyers")
public class BuyerController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("{buyerId}/cartnotcompleted")
    public ShoppingCartDTO getShoppingCartByBuyerNotCompleted(@PathVariable Long id){
        Optional<ShoppingCart> cart = shoppingCartService.getShoppingCartByBuyerNotCompleted(id);
        if(cart.isPresent()){
            return modelMapper.map(cart.get(), ShoppingCartDTO.class);
        }
        return null;
    }
}
