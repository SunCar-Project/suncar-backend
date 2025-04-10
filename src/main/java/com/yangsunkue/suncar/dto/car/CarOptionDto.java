package com.yangsunkue.suncar.dto.car;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yangsunkue.suncar.common.enums.OptionInstallStatus;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarOptionDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long carId;
    private String optionName;
    private OptionInstallStatus installStatus;
}
