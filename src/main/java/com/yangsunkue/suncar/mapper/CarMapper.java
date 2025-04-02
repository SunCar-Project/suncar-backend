package com.yangsunkue.suncar.mapper;

import com.yangsunkue.suncar.dto.car.*;
import com.yangsunkue.suncar.dto.car.response.RegisterCarResponseDto;
import com.yangsunkue.suncar.entity.car.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 차량 관련 모든 엔티티 매퍼 인터페이스 입니다.
 * Car
 * CarAccident
 * CarAccidentRepair
 * CarListing
 * CarListingImage
 * CarMileage
 * CarOption
 * CarOwnershipChange
 * CarUsage
 * Model
 */
@Mapper(config = BaseMapperConfig.class, uses = {EntityMapper.class})
public interface CarMapper {

    /** to Entity */
    @Mapping(source = "modelId", target = "model")
    Car fromCarDto(CarDto dto);
    @Mapping(source = "carId", target = "car")
    CarAccident fromAccidentDto(CarAccidentDto dto);
    @Mapping(source = "accidentId", target = "carAccident")
    CarAccidentRepair fromAccidentRepairDto(CarAccidentRepairDto dto);
    @Mapping(source = "carId", target = "car")
    @Mapping(source = "sellerId", target = "user")
    CarListing fromListingDto(CarListingDto dto);
    @Mapping(source = "listingId", target = "carListing")
    CarListingImage fromListingImageDto(CarListingImageDto dto);
    @Mapping(source = "carId", target = "car")
    CarMileage fromMileageDto(CarMileageDto dto);
    @Mapping(source = "carId", target = "car")
    CarOption fromOptionDto(CarOptionDto dto);
    @Mapping(source = "carId", target = "car")
    CarOwnershipChange fromOwnershipChangeDto(CarOwnershipChangeDto dto);
    @Mapping(source = "carId", target = "car")
    CarUsage fromUsageDto(CarUsageDto dto);
    Model fromModelDto(ModelDto dto);

    /** to Dto */
    @Mapping(source = "carListing.id", target = "listingId")
    @Mapping(source = "carListing.price", target = "price")
    @Mapping(source = "model.brandName", target = "brandName")
    @Mapping(source = "car.carName", target = "carName")
    @Mapping(source = "car.carNumber", target = "carNumber")
    RegisterCarResponseDto toRegisterCarResponseDto(
            CarListing carListing,
            Car car,
            Model model
    );
}
