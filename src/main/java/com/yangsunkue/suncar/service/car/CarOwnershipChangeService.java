package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarOwnershipChangeDto;
import com.yangsunkue.suncar.entity.car.CarOwnershipChange;

import java.util.List;

/**
 * CarOwnershipChangeService 엔티티 관련 서비스 인터페이스 입니다.
 */
public interface CarOwnershipChangeService {

    /**
     * 차량 번호/소유자 변경이력 정보를 다수 생성합니다.
     */
    List<CarOwnershipChange> createChanges(List<CarOwnershipChangeDto> dtos);
}
