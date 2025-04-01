package com.yangsunkue.suncar.dto.car.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class CarListResponseDto {

    /** CarListing */
    private Long carListingId;
    private BigDecimal price;

    /** Model */
    private String brandName;

    /** Car */
    private String carName;
    private String fuelType;
    private Integer year;
    private Integer month;

    /** CarMileage */
    private Integer distance;


    /**
     * CarListingImage
     * 대표 이미지와 나머지 이미지들 분리
     */
    private String mainImageUrl;
    @Builder.Default
    private List<String> otherImageUrls = new ArrayList<>();
}
