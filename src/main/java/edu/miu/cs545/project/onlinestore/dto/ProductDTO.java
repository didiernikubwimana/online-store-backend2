package edu.miu.cs545.project.onlinestore.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductDTO {
    private long id;
    private String productName;
    private String producer;
    private String description;
    private String color;
    private String size;
    private Double price;
    private Double rating;
    private String photo;
    private Integer numReviews;
    private LocalDate dueDate;
    private int quantityInStock;
    private CategoryDTO category;

}