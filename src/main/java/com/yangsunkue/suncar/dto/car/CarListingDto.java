package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.common.enums.CarListingStatus;
import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.CarListing;
import com.yangsunkue.suncar.entity.user.User;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class CarListingDto {

    private Long carId;
    private Long sellerId;
    private BigDecimal price;
    private String description;
    private CarListingStatus status;
}
