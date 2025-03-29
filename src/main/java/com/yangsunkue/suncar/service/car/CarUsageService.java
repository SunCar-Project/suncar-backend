package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarUsageDto;
import com.yangsunkue.suncar.entity.car.CarUsage;

import java.util.List;

/**
 * CarUsage 엔티티 관련 서비스 인터페이스 입니다.
 */
public interface CarUsageService {

    /**
     * 차량 사용이력 정보를 다수 생성합니다.
     */
    List<CarUsage> createUsages(List<CarUsageDto> dtos);
}
