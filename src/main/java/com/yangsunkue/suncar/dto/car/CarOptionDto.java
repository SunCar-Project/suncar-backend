package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.common.enums.OptionInstallStatus;
import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.CarOption;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarOptionDto {

    private Long carId;
    private String optionName;
    private OptionInstallStatus installStatus;
}
