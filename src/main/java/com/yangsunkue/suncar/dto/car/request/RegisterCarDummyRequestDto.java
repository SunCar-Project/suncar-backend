package com.yangsunkue.suncar.dto.car.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class RegisterCarDummyRequestDto {

    private String mainImage;
    private List<String> additionalImages;
    private String carNumber;
    private BigDecimal price;
}
