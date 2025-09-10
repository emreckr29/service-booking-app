package com.Emre.ServiceCommerce.services.client;

import com.Emre.ServiceCommerce.dto.AdDetailsForClientDto;
import com.Emre.ServiceCommerce.dto.AdDto;
import com.Emre.ServiceCommerce.dto.ReservationDto;
import com.Emre.ServiceCommerce.dto.ReviewDto;

import java.util.List;

public interface ClientService {

    List<AdDto> getAllAds();

    List<AdDto> searchAdByName(String name);

    boolean bookService(ReservationDto reservationDto);

    AdDetailsForClientDto getAdDetailsByAdId(Long adId);

    List<ReservationDto> getAllBookingByUserId(Long userId);

    boolean giveReview(ReviewDto reviewDto);
}
