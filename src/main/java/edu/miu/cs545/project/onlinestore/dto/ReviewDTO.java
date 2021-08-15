package edu.miu.cs545.project.onlinestore.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewDTO {
    private long id;
    private LocalDate date;
    private String content;
    private int numberOfStars;
    private boolean approved;
    private UserDTO user;
}
