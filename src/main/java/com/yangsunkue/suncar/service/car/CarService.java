package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarListResponseDto;

import java.util.List;

public interface CarService {

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    public List<CarListResponseDto> getCarList();
}
