package com.yangsunkue.suncar.util;

import com.yangsunkue.suncar.common.enums.CarListingStatus;
import com.yangsunkue.suncar.common.enums.OptionInstallStatus;
import com.yangsunkue.suncar.dto.car.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 차량 판매등록 시 사용될 더미 데이터 생성을 위한 클래스 입니다.
 *
 * - 차량 관련 10개 엔티티에 대한 더미 데이터 생성 메서드를 제공합니다.
 * - 개발 환경에서만 활성화됩니다.
 */
@Component
@Profile("dev")
@RequiredArgsConstructor
public class CarDummyDataGenerator {

    /** Model */
    public ModelDto generateModelDto() {

        String uuid = RandomUtils.createUuid(6);
        String brandName = "브랜드-" + uuid;
        String modelName = "모델-" + uuid;


        return ModelDto.builder()
                .brandName(brandName)
                .modelName(modelName)
                .isForeign(RandomUtils.randomBoolean())
                .build();
    }

    /** Car */
    public CarDto generateCarDto(
            Long modelId,
            String carNumber
    ) {

        String uuid = RandomUtils.createUuid(6);
        String carName = "자동차명-" + uuid;
        String displacement = "3998-" + uuid;
        String fuelType = "가솔린-" + uuid;
        Integer year = 2025;
        Integer month = 11;
        String bodyShape = "쿠페" + uuid;
        String modelType = "자가용 승용" + uuid;
        LocalDate firstInsuranceDate = LocalDate.now();
        String identificationNumber = "차대번호-" + uuid;
        BigDecimal minPrice = BigDecimal.valueOf(5000);
        BigDecimal maxPrice = BigDecimal.valueOf(9000);

        return CarDto.builder()
                .modelId(modelId)
                .carName(carName)
                .carNumber(carNumber)
                .displacement(displacement)
                .fuelType(fuelType)
                .year(year)
                .month(month)
                .bodyShape(bodyShape)
                .modelType(modelType)
                .firstInsuranceDate(firstInsuranceDate)
                .identificationNumber(identificationNumber)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();
    }

    /** CarMileage */
    public List<CarMileageDto> generateCarMileageDtos(Long carId) {

        String uuid = RandomUtils.createUuid(6);
        List<CarMileageDto> mileageList = new ArrayList<>();

        Integer distance1 = 25890;
        String provider1 = "제공처-" + uuid;
        LocalDate recordDate1 = LocalDate.now();

        CarMileageDto mileage1 = CarMileageDto.builder()
                .carId(carId)
                .distance(distance1)
                .provider(provider1)
                .recordDate(recordDate1)
                .build();

        Integer distance2 = 35555;
        String provider2 = "제공처2-" + uuid;
        LocalDate recordDate2 = LocalDate.now();

        CarMileageDto mileage2 = CarMileageDto.builder()
                .carId(carId)
                .distance(distance2)
                .provider(provider2)
                .recordDate(recordDate2)
                .build();

        mileageList.add(mileage1);
        mileageList.add(mileage2);

        return mileageList;
    }

    /** CarAccident */
    public List<CarAccidentDto> generateCarAccidentDtos(Long carId) {

        String uuid = RandomUtils.createUuid(6);
        List<CarAccidentDto> accidentList = new ArrayList<>();

        LocalDate accidentDate1 = LocalDate.now();
        String accidentType1 = "내차 피해-" + uuid;
        String processingType1 = "내 보험처리-" + uuid;

        CarAccidentDto accident1 = CarAccidentDto.builder()
                .carId(carId)
                .accidentDate(accidentDate1)
                .accidentType(accidentType1)
                .processingType(processingType1)
                .build();

        LocalDate accidentDate2 = LocalDate.now();
        String accidentType2 = "상대차 피해-" + uuid;
        String processingType2 = "상대 보험처리-" + uuid;

        CarAccidentDto accident2 = CarAccidentDto.builder()
                .carId(carId)
                .accidentDate(accidentDate2)
                .accidentType(accidentType2)
                .processingType(processingType2)
                .build();

        accidentList.add(accident1);
        accidentList.add(accident2);

        return accidentList;
    }

    /** CarAccidentRepair */
    public List<CarAccidentRepairDto> generateCarAccidentRepairDtos(List<Long> accidentIds) {

        String uuid = RandomUtils.createUuid(6);
        List<CarAccidentRepairDto> accidentRepairList = new ArrayList<>();

        String accidType1 = "전손-" + uuid;
        String totalAmount1 = "5000000-" + uuid;
        BigDecimal partsCost1 = BigDecimal.valueOf(900000);
        BigDecimal laborCost1 = BigDecimal.valueOf(1100000);
        BigDecimal paintingCost1 = BigDecimal.valueOf(2000000);
        BigDecimal insuranceAmount1 = BigDecimal.valueOf(1000000);

        CarAccidentRepairDto accidentRepair1 = CarAccidentRepairDto.builder()
                .accidentId(accidentIds.get(0))
                .accidType(accidType1)
                .totalAmount(totalAmount1)
                .partsCost(partsCost1)
                .laborCost(laborCost1)
                .paintingCost(paintingCost1)
                .insuranceAmount(insuranceAmount1)
                .build();

        String accidType2 = "도난-" + uuid;
        String totalAmount2 = "3000000-" + uuid;
        BigDecimal partsCost2 = BigDecimal.valueOf(900000);
        BigDecimal laborCost2 = BigDecimal.valueOf(100000);
        BigDecimal paintingCost2 = BigDecimal.valueOf(1400000);
        BigDecimal insuranceAmount2 = BigDecimal.valueOf(600000);

        CarAccidentRepairDto accidentRepair2 = CarAccidentRepairDto.builder()
                .accidentId(accidentIds.get(1))
                .accidType(accidType2)
                .totalAmount(totalAmount2)
                .partsCost(partsCost2)
                .laborCost(laborCost2)
                .paintingCost(paintingCost2)
                .insuranceAmount(insuranceAmount2)
                .build();

        accidentRepairList.add(accidentRepair1);
        accidentRepairList.add(accidentRepair2);

        return accidentRepairList;
    }

    /** CarOwnershipChange */
    public List<CarOwnershipChangeDto> generateCarOwnershipChangeDtos(Long carId) {

        String uuid = RandomUtils.createUuid(6);
        List<CarOwnershipChangeDto> ownershipChangeList = new ArrayList<>();

        LocalDate changeDate1 = LocalDate.now();
        String changeType1 = "소유자 변경-" + uuid;
        String carNumber1 = "234가2345-" + uuid;
        String usagePurpose1 = "자가용 승용-" + uuid;

        CarOwnershipChangeDto ownershipChange1 = CarOwnershipChangeDto.builder()
                .carId(carId)
                .changeDate(changeDate1)
                .changeType(changeType1)
                .carNumber(carNumber1)
                .usagePurpose(usagePurpose1)
                .build();

        LocalDate changeDate2 = LocalDate.now();
        String changeType2 = "소유자 변경2-" + uuid;
        String carNumber2 = "345가3456-" + uuid;
        String usagePurpose2 = "자가용 승용2-" + uuid;

        CarOwnershipChangeDto ownershipChange2 = CarOwnershipChangeDto.builder()
                .carId(carId)
                .changeDate(changeDate2)
                .changeType(changeType2)
                .carNumber(carNumber2)
                .usagePurpose(usagePurpose2)
                .build();

        ownershipChangeList.add(ownershipChange1);
        ownershipChangeList.add(ownershipChange2);

        return ownershipChangeList;
    }

    /** CarUsage */
    public List<CarUsageDto> generateCarUsageDtos(Long carId) {

        List<CarUsageDto> usageList = new ArrayList<>();

        String rentalHistory1 = "있음";
        String businessHistory1 = "있음";
        String governmentHistory1 = "없음";

        CarUsageDto usage1 = CarUsageDto.builder()
                .carId(carId)
                .rentalHistory(rentalHistory1)
                .businessHistory(businessHistory1)
                .governmentHistory(governmentHistory1)
                .build();

        String rentalHistory2 = "없음";
        String businessHistory2 = "없음";
        String governmentHistory2 = "있음";

        CarUsageDto usage2 = CarUsageDto.builder()
                .carId(carId)
                .rentalHistory(rentalHistory2)
                .businessHistory(businessHistory2)
                .governmentHistory(governmentHistory2)
                .build();

        usageList.add(usage1);
        usageList.add(usage2);

        return usageList;
    }

    /** CarOption */
    public List<CarOptionDto> generateCarOptionDtos(Long carId) {

        String uuid = RandomUtils.createUuid(6);
        List<CarOptionDto> optionList = new ArrayList<>();

        String optionName1 = "옵션1-" + uuid;
        OptionInstallStatus installStatus1 = OptionInstallStatus.INSTALLED;
        CarOptionDto option1 = CarOptionDto.builder()
                .carId(carId)
                .optionName(optionName1)
                .installStatus(installStatus1)
                .build();

        String optionName2 = "옵션2-" + uuid;
        OptionInstallStatus installStatus2 = OptionInstallStatus.NOT_INSTALLED;
        CarOptionDto option2 = CarOptionDto.builder()
                .carId(carId)
                .optionName(optionName2)
                .installStatus(installStatus2)
                .build();

        optionList.add(option1);
        optionList.add(option2);

        return optionList;
    }

    /** CarListing */
    public CarListingDto generateCarListingDto(
            Long carId,
            Long sellerId,
            BigDecimal price
    ) {

        String uuid = RandomUtils.createUuid(6);
        String description = "차량설명-" + uuid;
        CarListingStatus status = CarListingStatus.FOR_SALE;

        return CarListingDto.builder()
                .carId(carId)
                .sellerId(sellerId)
                .price(price)
                .description(description)
                .status(status)
                .build();
    }

    /** CarListingImage -> 메인 이미지 등록 (1장) */
    public CarListingImageDto generateCarListingImageDtoFromMainImage(
            Long listingId,
            String mainImage
    ) {

        Boolean isPrimary = true;

        return CarListingImageDto.builder()
                .listingId(listingId)
                .imageUrl(mainImage)
                .isPrimary(isPrimary)
                .build();
    }

    /** CarListingImage -> 일반 이미지 등록 (여러장) */
    public List<CarListingImageDto> generateCarListingDtosFromAdditionalImages(
            Long listingId,
            List<String> additionalImages
    ) {

        List<CarListingImageDto> images = new ArrayList<>();
        for (String imageUrl : additionalImages) {
            CarListingImageDto image = CarListingImageDto.builder()
                    .listingId(listingId)
                    .imageUrl(imageUrl)
                    .isPrimary(false)
                    .build();

            images.add(image);
        }
        return images;
    }
}
