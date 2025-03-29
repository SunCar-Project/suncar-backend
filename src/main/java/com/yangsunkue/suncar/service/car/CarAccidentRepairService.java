package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarAccidentRepairDto;
import com.yangsunkue.suncar.entity.car.CarAccidentRepair;

import java.util.List;

/**
 * CarAccidentRepair 엔티티 관련 서비스 인터페이스 입니다.
 */
public interface CarAccidentRepairService {

    /**
     * 차량 사고이력 상세정보를 다수 생성합니다.
     */
    List<CarAccidentRepair> createAccidentRepairs(List<CarAccidentRepairDto> dtos);
}
