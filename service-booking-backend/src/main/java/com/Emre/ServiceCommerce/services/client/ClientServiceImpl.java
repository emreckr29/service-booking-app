package com.Emre.ServiceCommerce.services.client;


import com.Emre.ServiceCommerce.dto.AdDetailsForClientDto;
import com.Emre.ServiceCommerce.dto.AdDto;
import com.Emre.ServiceCommerce.dto.ReservationDto;
import com.Emre.ServiceCommerce.dto.ReviewDto;
import com.Emre.ServiceCommerce.entities.Ad;
import com.Emre.ServiceCommerce.entities.Reservation;
import com.Emre.ServiceCommerce.entities.Review;
import com.Emre.ServiceCommerce.entities.User;
import com.Emre.ServiceCommerce.enums.ReservationStatus;
import com.Emre.ServiceCommerce.enums.ReviewStatus;
import com.Emre.ServiceCommerce.repositories.AdRepository;
import com.Emre.ServiceCommerce.repositories.ReservationRepository;
import com.Emre.ServiceCommerce.repositories.ReviewRepository;
import com.Emre.ServiceCommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReviewRepository reviewRepository;


    public List<AdDto> getAllAds(){
        return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public List<AdDto> searchAdByName(String name){
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }
    
    public boolean bookService(ReservationDto reservationDto){
        Optional<Ad> optionalAd = adRepository.findById(reservationDto.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());

        if (optionalUser.isPresent() && optionalAd.isPresent()){

            Reservation reservation = new Reservation();
            reservation.setBookDate(reservationDto.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());

            reservation.setAd(optionalAd.get());
            reservation.setCompany(optionalAd.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    public AdDetailsForClientDto getAdDetailsByAdId(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        AdDetailsForClientDto adDetailsForClientDto = new AdDetailsForClientDto();
        if (optionalAd.isPresent()){
            adDetailsForClientDto.setAdDto(optionalAd.get().getAdDto());

            List<Review> reviewList = reviewRepository.findAllByAdId(optionalAd.get().getId());

            adDetailsForClientDto.setReviewDtos(reviewList.stream().map(Review::getReviewDto).collect(Collectors.toList()));

        }
        System.out.println(adDetailsForClientDto.getReviewDtos());
        return adDetailsForClientDto;

    }

  ;;  public List<ReservationDto> getAllBookingByUserId(Long userId){
        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }


    public boolean giveReview(ReviewDto reviewDto){
        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
        Optional<Reservation> optionalReservation = reservationRepository.findById(reviewDto.getReservationId());

        if (optionalUser.isPresent() && optionalReservation.isPresent()){
            Review review = new Review();

            review.setReview(reviewDto.getReview());
            review.setReviewDate(new Date());
            review.setUser(optionalUser.get());
            review.setRating(reviewDto.getRating());
            review.setAd(optionalReservation.get().getAd());

            reviewRepository.save(review);

            Reservation res = optionalReservation.get();

            res.setReviewStatus(ReviewStatus.TRUE);

            reservationRepository.save(res);

            return true;
        }
        return false;
    }


}
