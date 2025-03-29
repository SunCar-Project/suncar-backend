package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarMileageDto;
import com.yangsunkue.suncar.entity.car.CarMileage;

import java.util.List;

/**
 * CarMileage 엔티티 관련 서비스 인터페이스 입니다.
 */
public interface CarMileageService {

    /**
     * 차량 주행거리 정보를 다수 생성합니다.
     */
    List<CarMileage> createMileages(List<CarMileageDto> dtos);
}
