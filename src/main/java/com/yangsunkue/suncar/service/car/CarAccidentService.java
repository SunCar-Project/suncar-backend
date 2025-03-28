package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarAccidentDto;
import com.yangsunkue.suncar.entity.car.CarAccident;

import java.util.List;

/**
 * CarAccident 엔티티 관련 서비스 클래스 입니다.
 */
public interface CarAccidentService {

    /**
     * 차량 사고이력(CarAccident)을 다수 생성합니다.
     */
    public List<CarAccident> createCarAccidentList(List<CarAccidentDto> dtoList);
}
