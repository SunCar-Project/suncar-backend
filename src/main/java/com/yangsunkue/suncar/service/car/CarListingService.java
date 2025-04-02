package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarListingDto;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.entity.car.CarListing;

import java.util.List;

/**
 * CarListing 엔티티 관련 서비스 인터페이스 입니다.
 */
public interface CarListingService {

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    List<CarListResponseDto> getCarList();

    /**
     * 차량 판매등록 정보를 생성합니다.
     */
    CarListing createListing(CarListingDto dto);
}
