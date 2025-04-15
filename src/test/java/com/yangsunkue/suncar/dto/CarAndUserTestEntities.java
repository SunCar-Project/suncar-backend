package com.yangsunkue.suncar.dto;


import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.User;

/**
 * User, Car 관련 모든 테스트용 엔티티를 담는 Dto 입니다.
 */
public record CarAndUserTestEntities(
        User user,
        Model model,
        Car car,
        CarListing carListing,
        CarAccident accident,
        CarAccidentRepair repair,
        CarListingImage mainImage,
        CarListingImage additionalImage,
        CarMileage mileage,
        CarOption option,
        CarOwnershipChange ownershipChange,
        CarUsage usage
) {
}
