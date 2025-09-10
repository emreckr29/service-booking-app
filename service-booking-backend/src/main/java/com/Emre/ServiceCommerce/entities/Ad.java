package com.Emre.ServiceCommerce.entities;

import com.Emre.ServiceCommerce.dto.AdDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ads")
@Data
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;
    private String description;
    private Double price;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] image;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    public AdDto getAdDto() {
        AdDto adDto = new AdDto();

        adDto.setId(id);
        adDto.setServiceName(serviceName);
        adDto.setPrice(price);
        adDto.setDescription(description);
        adDto.setReturnedImage(image);
        adDto.setCompanyName(user.getName());

        return adDto;
    }
}
