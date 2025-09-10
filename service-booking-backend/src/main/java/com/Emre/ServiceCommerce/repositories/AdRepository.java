package com.Emre.ServiceCommerce.repositories;

import com.Emre.ServiceCommerce.dto.AdDto;
import com.Emre.ServiceCommerce.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findAllByUserId(Long userId);

    List<Ad> findAllByServiceNameContaining(String name);
}
