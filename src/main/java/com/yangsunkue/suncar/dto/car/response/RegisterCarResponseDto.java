package com.yangsunkue.suncar.dto.car.response;


import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class RegisterCarResponseDto {

    /** CarListing */
    private Long listingId;
    private BigDecimal price;

    /** Model */
    private String brandName;

    /** Car */
    private String carName;
    private String carNumber;
}