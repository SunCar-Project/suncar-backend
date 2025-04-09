package com.yangsunkue.suncar.repository.car;

import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.repository.CarDetailFetchResult;

import java.util.List;
import java.util.Optional;

/**
 * CarListing Custom Repository 입니다.
 */
public interface CarListingRepositoryCustom {

    List<CarListResponseDto> getCarList();

    Optional<CarDetailFetchResult> getCarDetailById(Long listingId);
}
