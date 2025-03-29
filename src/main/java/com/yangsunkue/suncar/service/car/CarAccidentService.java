package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarAccidentDto;
import com.yangsunkue.suncar.entity.car.CarAccident;

import java.util.List;

/**
 * CarAccident 엔티티 관련 서비스 인터페이스 입니다.
 */
public interface CarAccidentService {

    /**
     * 차량 사고이력을 다수 생성합니다.
     */
    List<CarAccident> createAccidents(List<CarAccidentDto> dtos);
}
