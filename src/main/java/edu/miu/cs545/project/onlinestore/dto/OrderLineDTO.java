package edu.miu.cs545.project.onlinestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineDTO {
    private long id;
    private int quantity;
    private Double price;
    private Double lineTotal;
    ProductDTO product;
}
