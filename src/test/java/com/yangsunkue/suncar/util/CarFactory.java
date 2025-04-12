package com.yangsunkue.suncar.util;

import com.yangsunkue.suncar.common.enums.CarListingStatus;
import com.yangsunkue.suncar.entity.car.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CarFactory {

    public static Model createModel() {
        return Model.builder()
                .id(1L)
                .brandName("테스트브랜드")
                .modelName("테스트모델")
                .isForeign(true)
                .build();
    }

    public static Car createCar() {
        return Car.builder()
                .id(1L)
                .model(createModel())
                .carName("테스트자동차명")
                .carNumber("123가1234")
                .displacement("2998")
                .fuelType("가솔린")
                .year(2025)
                .bodyShape("왜건")
                .modelType("자가용 승용")
                .firstInsuranceDate(LocalDate.of(2025, 04, 10))
                .identificationNumber("테스트차대번호")
                .build();
    }

    public static CarListing createCarListing() {
        return CarListing.builder()
                .id(1L)
                .car(createCar())
                .user(UserFactory.createUser())
                .price(BigDecimal.valueOf(5000))
                .description("테스트설명")
                .status(CarListingStatus.FOR_SALE)
                .build();

    }

    /**
     *
     * @param isPrimary - 메인/일반 이미지를 결정합니다.
     *                  true
     *                      id: 1L,
     *                      imageUrl: "테스트메인이미지URL",
     *                      isPrimary: true
     *                  false
     *                      id: 2L,
     *                      imageUrl: "테스트이미지URL",
     *                      isPrimary: false
     */
    public static CarListingImage createCarListingImageByIsPrimary(Boolean isPrimary) {

        String mainImageUrl = "테스트메인이미지URL";
        String additionalImageUrl = "테스트이미지URL";

        return CarListingImage.builder()
                .id(isPrimary ? 1L : 2L)
                .carListing(createCarListing())
                .imageUrl(isPrimary ? mainImageUrl : additionalImageUrl)
                .isPrimary(isPrimary)
                .build();
    }

    public static CarMileage createCarMileage() {
        return CarMileage.builder()
                .id(1L)
                .car(createCar())
                .distance(20000)
                .provider("테스트제공처")
                .recordDate(LocalDate.of(2025, 04, 10))
                .build();
    }

    public static CarUsage createCarUsage() {
        return CarUsage.builder()
                .id(1L)
                .car(createCar())
                .rentalHistory("없음")
                .businessHistory("없음")
                .governmentHistory("없음")
                .build();
    }
}
