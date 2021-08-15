package edu.miu.cs545.project.onlinestore.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @NotNull
    private LocalDate paymentDate;

    @NotNull
//    @Size(min = 10, max = 500)
    private Double paymentAmount;

    @NotNull
    private String cardHolder;

    private String paymentMethod;

    public Payment(LocalDate now, Double i, String john, String credit) {
        this.paymentDate = now;
        this.paymentAmount = i;
        this.cardHolder = john;
        this.paymentMethod = credit;

    }
}