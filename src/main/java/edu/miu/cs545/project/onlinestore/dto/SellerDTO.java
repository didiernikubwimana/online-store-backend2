package edu.miu.cs545.project.onlinestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerDTO {
    private long id;
    private boolean approved;
    private String companyName;
    UserDTO user;
}

