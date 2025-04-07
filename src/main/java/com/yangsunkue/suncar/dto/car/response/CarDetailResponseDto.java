package com.yangsunkue.suncar.dto.car.response;

import com.yangsunkue.suncar.common.enums.CarListingStatus;
import com.yangsunkue.suncar.dto.car.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Setter
public class CarDetailResponseDto {

    /** CarListing */
    private Long id;
    private BigDecimal price;
    private String description;
    private CarListingStatus status;

    /** User */
    private Long sellerId;
    private String sellerUserName;

    /** Car */
    private Long carId;
    private String carName;
    private String carNumber;
    private String displacement;
    private String fuelType;
    private Integer year;
    private Integer month;
    private String bodyShape;
    private String modelType;
    private LocalDate firstInsuranceDate;
    private String identificationNumber;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    /** Model */
    private String brandName;
    private String modelName;
    private Boolean isForeign;

    /** CarListingImage */
    private String mainImageUrl;
    private List<String> additionalImageUrls;

    /**
     * CarAccident
     * CarMileage
     * CarOption
     * CarOwnershipChange
     * CarUsage
     */
    private List<CarAccidentWithRepairsDto> accidents;
    private List<CarMileageDto> mileages;
    private List<CarOptionDto> options;
    private List<CarOwnershipChangeDto> ownershipChanges;
    private CarUsageDto usage;


}
