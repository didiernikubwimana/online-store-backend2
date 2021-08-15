package edu.miu.cs545.project.onlinestore.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PaymentDTO {
    private long id;
    private LocalDate paymentDate;
    private Double paymentAmount;
    private String cardHolder;
    private String paymentMethod;
}