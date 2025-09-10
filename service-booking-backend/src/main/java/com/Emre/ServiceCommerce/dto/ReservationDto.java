package com.Emre.ServiceCommerce.dto;

import com.Emre.ServiceCommerce.enums.ReservationStatus;
import com.Emre.ServiceCommerce.enums.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {

    private Long id;

    private Date bookDate;

    private String serviceName;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private Long userId;

    private String userName;

    private Long companyId;

    private Long adId;
}
