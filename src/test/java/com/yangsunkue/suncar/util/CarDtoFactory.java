package com.yangsunkue.suncar.util;

import com.yangsunkue.suncar.dto.car.*;
import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.User;

public class CarDtoFactory {

    public static ModelDto createModelDto() {
        Model model = CarFactory.createModel();

        return ModelDto.builder()
                .brandName(model.getBrandName())
                .modelName(model.getModelName())
                .isForeign(model.getIsForeign())
                .build();
    }

    public static CarDto createCarDto() {
        Car car = CarFactory.createCar();
        Model model = CarFactory.createModel();

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
        CarListing carListing = CarFactory.createCarListing();
        Car car = CarFactory.createCar();
        User user = UserFactory.createUser();

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
        CarListingImage carListingImage = CarFactory.createCarListingImageByIsPrimary(isPrimary);
        CarListing carListing = CarFactory.createCarListing();

        return CarListingImageDto.builder()
                .listingId(carListing.getId())
                .imageUrl(carListingImage.getImageUrl())
                .isPrimary(carListingImage.getIsPrimary())
                .build();
    }

    public static CarMileageDto createCarMileageDto() {
        Car car = CarFactory.createCar();
        CarMileage carMileage = CarFactory.createCarMileage();

        return CarMileageDto.builder()
                .carId(car.getId())
                .distance(carMileage.getDistance())
                .provider(carMileage.getProvider())
                .recordDate(carMileage.getRecordDate())
                .build();
    }

    public static CarUsageDto createCarUsageDto() {
        Car car = CarFactory.createCar();
        CarUsage carUsage = CarFactory.createCarUsage();

        return CarUsageDto.builder()
                .carId(car.getId())
                .rentalHistory(carUsage.getRentalHistory())
                .businessHistory(carUsage.getBusinessHistory())
                .governmentHistory(carUsage.getGovernmentHistory())
                .build();
    }
}
