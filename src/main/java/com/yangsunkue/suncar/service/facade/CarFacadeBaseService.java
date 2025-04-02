package com.yangsunkue.suncar.service.facade;

import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;

import java.util.List;

/**
 * 차량 관련 Facade Base 서비스 입니다.
 */
public interface CarFacadeBaseService {

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    List<CarListResponseDto> getCarList();
}
