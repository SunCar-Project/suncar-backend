package com.yangsunkue.suncar.repository.car;

import com.yangsunkue.suncar.dto.car.CarListResponseDto;

import java.util.List;

/**
 * CarListing Custom Repository 입니다.
 */
public interface CarListingRepositoryCustom {
    List<CarListResponseDto> getCarList();
}
