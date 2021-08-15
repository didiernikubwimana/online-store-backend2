package edu.miu.cs545.project.onlinestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerDTO {
    private long id;
    private int accumulatedPoints;
    UserDTO user;
}

