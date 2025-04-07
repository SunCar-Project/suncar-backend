package com.yangsunkue.suncar.dto.repository;

import com.yangsunkue.suncar.entity.car.*;

import java.util.List;
import java.util.Map;

/** 차량 상세조회 결과 래핑 레코드 입니다.
 * CarListingRepositoryCustomImpl - getCarDetailById(Long listingId)
 */
public record CarDetailFetchResult (
        CarListing carListing,
        List<CarListingImage> images,
        List<CarAccident> accidents,
        Map<Long, List<CarAccidentRepair>> repairsByAccidentId,
        List<CarMileage> mileages,
        List<CarOption> options,
        List<CarOwnershipChange> ownershipChanges,
        CarUsage usage
) {

}