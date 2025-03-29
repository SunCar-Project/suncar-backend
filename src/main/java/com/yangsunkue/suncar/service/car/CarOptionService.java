package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarOptionDto;
import com.yangsunkue.suncar.entity.car.CarOption;

import java.util.List;

/**
 * CarOption 엔티티 관련 서비스 인터페이스 입니다.
 */
public interface CarOptionService {

    /**
     * 차량 안전장치/옵션 정보를 다수 생성합니다.
     */
    List<CarOption> createOptions (List<CarOptionDto> dtos);
}
