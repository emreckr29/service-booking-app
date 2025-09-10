package com.Emre.ServiceCommerce.dto;


import com.Emre.ServiceCommerce.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdDto {


    private Long id;

    private String serviceName;
    private String description;
    private Double price;
    private MultipartFile image;
    private byte[] returnedImage;
    private Long userId;
    private String companyName;
}
