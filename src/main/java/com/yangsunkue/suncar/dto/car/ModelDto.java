package com.yangsunkue.suncar.dto.car;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class ModelDto {

    private String brandName;
    private String modelName;
    private Boolean isForeign;
}
