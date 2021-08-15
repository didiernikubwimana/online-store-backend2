package edu.miu.cs545.project.onlinestore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @NotNull
    private String productName;

    private String producer;

    private String description;

    private String color;

    @Size(min = 1, max = 5)
    private String size;

   @Digits(integer = 10, fraction = 2)
    private Double price;

    private LocalDate dueDate;

    private int quantityInStock;

    private String photo;

    private Double rating;

    private Integer numReviews;

    @OneToMany(mappedBy="product")
    private List<Review> reviews = new ArrayList();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seller_id")
    Seller seller;
}
