package edu.miu.cs545.project.onlinestore.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartDTO {
    private long id;
    private LocalDate cartDate;
    private Double totalMoney;
    private boolean completed;
    private List<ShoppingCartLineDTO> cartLines = new ArrayList();
}
