package edu.miu.cs545.project.onlinestore.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OrderDTO {
    private long id;
    private LocalDate orderDate;
    private Double totalMoney;
    private String currentStatus;
    private BuyerDTO buyer;
    private PaymentDTO payment;
    private ShippingDTO shipping;
    private List<OrderLineDTO> cartLines = new ArrayList();

}
