package edu.miu.cs545.project.onlinestore.controller;

import edu.miu.cs545.project.onlinestore.domain.*;
import edu.miu.cs545.project.onlinestore.dto.OrderDTO;
import edu.miu.cs545.project.onlinestore.service.*;
import edu.miu.cs545.project.onlinestore.service.UserDetailsImpl;
import edu.miu.cs545.project.onlinestore.utils.OrderPDFExporter;
import org.dom4j.DocumentException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BuyerService buyerService;

    public String getOrderStatus(long id){
        return orderService.getOrderStatus(id);
    }

    @GetMapping("/{orderId}")
    public @ResponseBody
    OrderDTO getOrderById(@PathVariable long id){
        Optional<Order> orderOptional = orderService.getOrderById(id);
        if(orderOptional.isPresent()){
            return modelMapper.map(orderOptional.get(), OrderDTO.class);
        }
        return null;
    }

    @GetMapping("/{orderId}/cancel")
    public @ResponseBody Boolean cancelOrder(@PathVariable long orderId){
        return orderService.cancelOrder(orderId);
    }

    @GetMapping
    public List<OrderDTO> getOrderForBuyer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Buyer> buyer =  buyerService.findAll().stream().filter(buy->buy.getUser().getUsername().equalsIgnoreCase(userDetails.getUsername())).findFirst();
        if(buyer.isPresent()){
            List<Order> orders = orderService.getOrderForBuyer(buyer.get().getId());
            return orders.stream()
                    .map(orderData -> modelMapper.map(orderData, OrderDTO.class))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public void createOrderFromCart(Long cartId, Shipping shipping, Payment payment){
        orderService.createOrderFromCart(cartId,shipping,payment);
    }

    public List<OrderLine> getOrderLineById(long id){
        return orderService.getOrderLineById(id);
    }

    @GetMapping("/export/pdf")
    public ResponseEntity exportToPDF() throws DocumentException, IOException, com.lowagie.text.DocumentException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<Buyer> buyer =  buyerService.findAll().stream().filter(buy->buy.getUser().getUsername().equalsIgnoreCase(userdetails.getUsername())).findFirst();
        if(buyer.isPresent()){
            List<Order> orders = orderService.getOrderForBuyer(buyer.get().getId());
            List<OrderDTO> orderDTOS = orders.stream()
                    .map(x -> modelMapper.map(x, OrderDTO.class))
                    .collect(Collectors.toList());
            OrderPDFExporter exporter = new OrderPDFExporter(orderDTOS);
            ByteArrayInputStream ba = exporter.export();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=orders.pdf");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(ba));
        }
        return null;
    }


}

