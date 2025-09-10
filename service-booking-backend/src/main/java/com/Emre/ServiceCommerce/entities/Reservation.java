package com.Emre.ServiceCommerce.entities;

import com.Emre.ServiceCommerce.dto.ReservationDto;
import com.Emre.ServiceCommerce.enums.ReservationStatus;
import com.Emre.ServiceCommerce.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private Date bookDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ad_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;


    public ReservationDto getReservationDto(){

        ReservationDto dto = new ReservationDto();

        dto.setId(id);
        dto.setReservationStatus(reservationStatus);
        dto.setReviewStatus(reviewStatus);
        dto.setBookDate(bookDate);
        dto.setServiceName(ad.getServiceName());

        dto.setAdId(ad.getId());
        dto.setUserId(user.getId());
        dto.setCompanyId(company.getId());

        return dto;
    }
}
