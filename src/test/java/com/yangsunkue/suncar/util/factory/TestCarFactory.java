package com.yangsunkue.suncar.util.factory;

import com.yangsunkue.suncar.common.enums.CarListingStatus;
import com.yangsunkue.suncar.common.enums.OptionInstallStatus;
import com.yangsunkue.suncar.entity.car.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestCarFactory {

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
                .user(TestUserFactory.createUser())
                .price(BigDecimal.valueOf(5000))
                .description("테스트설명")
                .status(CarListingStatus.FOR_SALE)
                .build();
    }

    public static CarAccident createCarAccident() {
        return CarAccident.builder()
                .id(1L)
                .car(createCar())
                .accidentDate(LocalDate.of(2025, 04, 10))
                .accidentType("내차 피해")
                .processingType("내차 보험처리")
                .build();
    }

    public static CarAccidentRepair createCarAccidentRepair() {
        return CarAccidentRepair.builder()
                .id(1L)
                .carAccident(createCarAccident())
                .accidType("전손")
                .totalAmount("3000000")
                .partsCost(BigDecimal.valueOf(1000000))
                .laborCost(BigDecimal.valueOf(1000000))
                .paintingCost(BigDecimal.valueOf(1000000))
                .insuranceAmount(BigDecimal.valueOf(3000000))
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

    public static CarOption createCarOption() {
        return CarOption.builder()
                .id(1L)
                .car(createCar())
                .optionName("비행모드")
                .installStatus(OptionInstallStatus.INSTALLED)
                .build();
    }

    public static CarOwnershipChange createCarOwnershipChange() {
        return CarOwnershipChange.builder()
                .id(1L)
                .car(createCar())
                .changeDate(LocalDate.of(2025, 04, 10))
                .changeType("차량번호 변경")
                .carNumber("234가2345")
                .usagePurpose("자가용 승용")
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
