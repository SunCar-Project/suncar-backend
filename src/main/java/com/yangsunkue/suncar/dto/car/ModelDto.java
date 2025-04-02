package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.common.enums.BrandName;
import com.yangsunkue.suncar.common.enums.ModelName;
import com.yangsunkue.suncar.entity.car.Model;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class ModelDto {

    private BrandName brandName;
    private ModelName modelName;
    private Boolean isForeign;
}
