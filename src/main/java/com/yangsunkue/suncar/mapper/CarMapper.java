package com.yangsunkue.suncar.mapper;

import com.yangsunkue.suncar.dto.car.*;
import com.yangsunkue.suncar.entity.car.*;
import org.mapstruct.Mapper;

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
@Mapper(config = BaseMapperConfig.class)
public interface CarMapper {

    /** to Entity */
    Car fromCarDto(CarDto dto);
    CarAccident fromAccidentDto(CarAccidentDto dto);
    CarAccidentRepair fromAccidentRepairDto(CarAccidentRepairDto dto);
    CarListing fromListingDto(CarListingDto dto);
    CarListingImage fromListingImageDto(CarListingImageDto dto);
    CarMileage fromMileageDto(CarMileageDto dto);
    CarOption fromOptionDto(CarOptionDto dto);
    CarOwnershipChange fromOwnershipChangeDto(CarOwnershipChangeDto dto);
    CarUsage fromUsageDto(CarUsageDto dto);
    Model fromModelDto(ModelDto dto);
}
