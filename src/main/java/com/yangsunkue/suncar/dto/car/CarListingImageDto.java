package com.yangsunkue.suncar.dto.car;

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
