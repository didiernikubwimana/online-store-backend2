package edu.miu.cs545.project.onlinestore.service;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.miu.cs545.project.onlinestore.domain.*;
import edu.miu.cs545.project.onlinestore.repository.ShoppingCartRepository;
import edu.miu.cs545.project.onlinestore.repository.OrderRepository;
import edu.miu.cs545.project.onlinestore.repository.OrderLineRepository;
import edu.miu.cs545.project.onlinestore.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ShippingService shippingService;
    @Autowired
    private PaymentService paymentService;

    @Override
    public Optional<Order> getOrderById(long id){
        return orderRepository.findById(id);
    }

    @Override
    public String getOrderStatus(long orderId){//checked
        return orderRepository.findById(orderId).get().getCurrentStatus();
    }

    @Override
    public List<OrderLine> getOrderLineById(long id){
        List<OrderLine> listOrderLine = new ArrayList<>();
        return orderLineRepository.getOrderLineById(id);
    }

    @Override
    public Order createOrder(Order newOrder){
        return orderRepository.save(newOrder);
    }



    @Override
    public List<Order> getOrderForBuyer(long id) {
        return orderRepository.findAllByBuyerId(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    void sendEmail(String emailAddress, Order order) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(emailAddress, emailAddress);
            msg.setSubject("Purchase was successful");
            String content = "";
            content += order.getId() + "\n";
            content += order.getOrderDate().toString() + "\n";
            content += order.getPayment().getPaymentMethod() + "\n";
            content += order.getTotalMoney().toString() + "\n";
            content += order.getShipping().toString() + "\n";
            content += "THANK YOU FOR SHOPPING WITH US.";
            msg.setText(content);

            javaMailSender.send(msg);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean shippedOrder(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if(order != null)
        {
            order.setCurrentStatus("SHIPPED");
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    public void createOrderFromCart(Long cartId, Shipping shipping, Payment payment){
        Order order = new Order();
        Payment paymentData = paymentService.createPayment(payment);
        Shipping shippingNew = shippingService.createShipping(shipping);
        Optional<ShoppingCart> cart = shoppingCartService.getShoppingCart(cartId);
        if(cart.isPresent()){
            ShoppingCart shCart = cart.get();
            order.setCurrentStatus("NEW");
            order.setOrderDate(LocalDate.now());
            order.setShipping(shippingNew);
            order.setPayment(paymentData);
            order.setTotalMoney(shCart.getTotalMoney());
            order.setBuyer(shCart.getBuyer());
            List<ShoppingCartLine> cartLines = shoppingCartService.getLinesByShoppingCart(cartId);
            cartLines.forEach(cartLine -> {
                OrderLine orderLine = createOrderLineFromCartLine(cartLine);
                orderLine.setOrder(order);
                orderLineRepository.save(orderLine);
            });
            Order orderNew = orderRepository.save(order);
            shCart.setCompleted(true);
            Buyer buyer = shCart.getBuyer();
            buyer.setAccumulatedPoints(buyer.getAccumulatedPoints() + 10);
            buyerRepository.save(buyer);
            shoppingCartRepository.save(shCart);
            sendEmail(buyer.getUser().getEmail(),orderNew);
        }
    }

    @Override
    public Boolean cancelOrder(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if(order != null)
        {
            order.setCurrentStatus("CANCELLED");
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deliveredOrder(long id) {
        Order order = orderRepository.findOrderById(id);
        if(order != null)
        {
            order.setCurrentStatus("DELIVERED");
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    private OrderLine createOrderLineFromCartLine(ShoppingCartLine cartLine){
        OrderLine line = new OrderLine();
        line.setProduct(cartLine.getProduct());
        line.setPrice(cartLine.getPrice());
        line.setLineTotal(cartLine.getLineTotal());
        line.setQuantity(cartLine.getQuantity());
        return line;
    }

    @Override
    public List<Order> getOrderBySellerId(long id) {
        List<OrderLine> lines = orderLineRepository.findAll().stream().filter(orderLine -> orderLine.getProduct().getSeller().getId() == id).collect(Collectors.toList());
        List<Long> ids = lines.stream().map( orderLine->orderLine.getId()).collect(Collectors.toList());
        List<Order> orders = orderRepository.findAll().stream().filter(ord->ids.contains(ord.getId())).collect(Collectors.toList());
        return orders;
    }
}
