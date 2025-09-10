package com.Emre.ServiceCommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdDetailsForClientDto {

    private AdDto adDto;

    private List<ReviewDto> reviewDtos;
}
