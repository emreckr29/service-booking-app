package com.Emre.ServiceCommerce.dto;

import com.Emre.ServiceCommerce.enums.UserRole;
import lombok.Data;

@Data
public class SignUpRequestDto {

    private Long id;

    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;

}
