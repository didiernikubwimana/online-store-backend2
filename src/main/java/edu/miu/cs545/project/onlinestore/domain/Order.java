package edu.miu.cs545.project.onlinestore.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    private LocalDate orderDate;

    @Digits(integer = 10, fraction = 2)
    @NotNull
    private Double totalMoney;

    private String currentStatus;

    @OneToMany(mappedBy="order")
    private List<OrderLine> cartLines = new ArrayList();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="buyer_id")
    Buyer buyer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="payment_id")
    Payment payment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="shipping_id")
    Shipping shipping;
}
