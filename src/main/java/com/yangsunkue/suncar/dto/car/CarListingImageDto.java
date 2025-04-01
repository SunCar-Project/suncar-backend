package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.entity.car.CarListing;
import com.yangsunkue.suncar.entity.car.CarListingImage;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class CarListingImageDto {

    private Long listingId;
    private String imageUrl;
    private Boolean isPrimary;
}
