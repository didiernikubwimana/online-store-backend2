package edu.miu.cs545.project.onlinestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserDTO extends UserDTO {
    private String password;
}

