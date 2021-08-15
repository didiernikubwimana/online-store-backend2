package edu.miu.cs545.project.onlinestore.controller;

import edu.miu.cs545.project.onlinestore.domain.Order;
import edu.miu.cs545.project.onlinestore.domain.Product;
import edu.miu.cs545.project.onlinestore.domain.Seller;
import edu.miu.cs545.project.onlinestore.dto.OrderDTO;
import edu.miu.cs545.project.onlinestore.dto.ProductDTO;
import edu.miu.cs545.project.onlinestore.dto.SellerDTO;
import edu.miu.cs545.project.onlinestore.service.OrderService;
import edu.miu.cs545.project.onlinestore.service.ProductService;
import edu.miu.cs545.project.onlinestore.service.SellerService;
import edu.miu.cs545.project.onlinestore.service.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/sellers")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @Autowired
    private ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<SellerDTO> getAll(){
        List<Seller> sellers = sellerService.getAll();
        return sellers.stream().map(sel->modelMapper.map(sel, SellerDTO.class)).collect(Collectors.toList());
    }
          // products by seller
    @GetMapping("/{id}/products")
    public List<ProductDTO> getProductsBySellerId(@PathVariable("id") Long id){
        List<Product> products = sellerService.getProductsBySellerId(id);
        return products.stream()
                .map(prod -> modelMapper.map(prod, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/notapproved")
    public List<SellerDTO> getNotApprovedSellers(){
        List<Seller> sellers = sellerService.getAll();
        return sellers.stream().filter(sel->!sel.isApproved()).map(seller->modelMapper.map(seller, SellerDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}/orders")
    public List<OrderDTO> getOrdersBySellerId(@PathVariable("id") Long id){
        List<Order> orders = orderService.getOrderBySellerId(id);
        return orders.stream()
                .map(ord -> modelMapper.map(ord, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SellerDTO getSellerById(@PathVariable("id") Long id){
        Seller seller = sellerService.getSellerByID(id);
        return modelMapper.map(seller, SellerDTO.class);
    }

    @GetMapping("/{orderId}/shipped")
    public @ResponseBody Boolean shippedOrder(@PathVariable long orderId){
        return orderService.shippedOrder(orderId);
    }

    @GetMapping("/{orderId}/cancel")
    public @ResponseBody Boolean cancelOrder(@PathVariable long orderId){
        return orderService.cancelOrder(orderId);
    }

    @PostMapping("/updateproduct")
    public Boolean updateProduct(@RequestBody ProductDTO productDTO){
        Product product = modelMapper.map(productDTO, Product.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) authentication.getPrincipal();
        return productService.updateProduct(product, userdetails.getUser().getId());
    }

    @GetMapping("/{orderId}/delivered")
    public @ResponseBody Boolean deliveredOrder(@PathVariable long orderId){
        return orderService.deliveredOrder(orderId);
    }

    @PostMapping("/newproduct")
    public Boolean createProduct(@RequestBody ProductDTO productDTO){
        Product product = modelMapper.map(productDTO, Product.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return productService.createProduct(product, userDetails.getUser().getId());
    }
}

