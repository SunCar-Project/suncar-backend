package com.yangsunkue.suncar.repository.car;

import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.repository.CarDetailFetchResult;

import java.util.List;
import java.util.Optional;

/**
 * CarListing Custom Repository 입니다.
 */
public interface CarListingRepositoryCustom {

    /**
     * 모든 판매중인 자동차 정보를 찾습니다.
     */
    List<CarListResponseDto> getCarList();

    /**
     * 차량 상세정보를 조회합니다.
     */
    Optional<CarDetailFetchResult> getCarDetailById(Long listingId);

    /**
     * 판매중인 차량을 soft delete 방식으로 삭제합니다.
     * CarListing 엔티티를 시작으로, 연관된 모든 엔티티를 함께 삭제합니다.
     */
    void softDeleteCarListingWithRelatedEntities(Long listingId);
}
