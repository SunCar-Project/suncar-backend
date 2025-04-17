package com.yangsunkue.suncar.util.factory;

import com.yangsunkue.suncar.dto.car.*;
import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.User;

public class TestCarDtoFactory {

    public static ModelDto createModelDto() {
        Model model = TestCarFactory.createModel();

        return ModelDto.builder()
                .brandName(model.getBrandName())
                .modelName(model.getModelName())
                .isForeign(model.getIsForeign())
                .build();
    }

    public static CarDto createCarDto() {
        Car car = TestCarFactory.createCar();
        Model model = TestCarFactory.createModel();

        return CarDto.builder()
                .modelId(model.getId())
                .carName(car.getCarName())
                .carNumber(car.getCarNumber())
                .displacement(car.getDisplacement())
                .fuelType(car.getFuelType())
                .year(car.getYear())
                .month(car.getMonth())
                .bodyShape(car.getBodyShape())
                .modelType(car.getModelType())
                .firstInsuranceDate(car.getFirstInsuranceDate())
                .identificationNumber(car.getIdentificationNumber())
                .minPrice(car.getMinPrice())
                .maxPrice(car.getMaxPrice())
                .build();

    }

    public static CarListingDto createCarListingDto() {
        CarListing carListing = TestCarFactory.createCarListing();
        Car car = TestCarFactory.createCar();
        User user = TestUserFactory.createUser();

        return CarListingDto.builder()
                .carId(car.getId())
                .sellerId(user.getId())
                .price(carListing.getPrice())
                .description(carListing.getDescription())
                .status(carListing.getStatus())
                .build();
    }

    /**
     *
     * @param isPrimary - 메인/일반 이미지를 결정합니다.
     *                  true
     *                      imageUrl: "테스트메인이미지URL"
     *                      isPrimary: true
     *                  false
     *                      imageUrl: "테스트이미지URL",
     *                      isPrimary: false
     */
    public static CarListingImageDto createCarListingImageDtoByIsPrimary(Boolean isPrimary) {
        CarListingImage carListingImage = TestCarFactory.createCarListingImageByIsPrimary(isPrimary);
        CarListing carListing = TestCarFactory.createCarListing();

        return CarListingImageDto.builder()
                .listingId(carListing.getId())
                .imageUrl(carListingImage.getImageUrl())
                .isPrimary(carListingImage.getIsPrimary())
                .build();
    }

    public static CarMileageDto createCarMileageDto() {
        Car car = TestCarFactory.createCar();
        CarMileage carMileage = TestCarFactory.createCarMileage();

        return CarMileageDto.builder()
                .carId(car.getId())
                .distance(carMileage.getDistance())
                .provider(carMileage.getProvider())
                .recordDate(carMileage.getRecordDate())
                .build();
    }

    public static CarUsageDto createCarUsageDto() {
        Car car = TestCarFactory.createCar();
        CarUsage carUsage = TestCarFactory.createCarUsage();

        return CarUsageDto.builder()
                .carId(car.getId())
                .rentalHistory(carUsage.getRentalHistory())
                .businessHistory(carUsage.getBusinessHistory())
                .governmentHistory(carUsage.getGovernmentHistory())
                .build();
    }
}
