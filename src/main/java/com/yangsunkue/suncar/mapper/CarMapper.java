package com.yangsunkue.suncar.mapper;

import com.yangsunkue.suncar.dto.car.*;
import com.yangsunkue.suncar.dto.car.response.CarDetailResponseDto;
import com.yangsunkue.suncar.dto.car.response.RegisterCarResponseDto;
import com.yangsunkue.suncar.dto.car.response.UpdateCarListingResponseDto;
import com.yangsunkue.suncar.entity.car.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "car.carName", target = "carName")
    @Mapping(source = "car.carNumber", target = "carNumber")
    @Mapping(source = "car.displacement", target = "displacement")
    @Mapping(source = "car.fuelType", target = "fuelType")
    @Mapping(source = "car.year", target = "year")
    @Mapping(source = "car.month", target = "month")
    @Mapping(source = "car.bodyShape", target = "bodyShape")
    @Mapping(source = "car.modelType", target = "modelType")
    @Mapping(source = "car.firstInsuranceDate", target = "firstInsuranceDate")
    @Mapping(source = "car.identificationNumber", target = "identificationNumber")
    @Mapping(source = "car.minPrice", target = "minPrice")
    @Mapping(source = "car.maxPrice", target = "maxPrice")
    @Mapping(source = "car.model.brandName", target = "brandName")
    @Mapping(source = "car.model.modelName", target = "modelName")
    @Mapping(source = "car.model.isForeign", target = "isForeign")
    @Mapping(source = "user.id", target = "sellerId")
    @Mapping(source = "user.username", target = "sellerUserName")
    @Mapping(target = "mainImageUrl", ignore = true)
    @Mapping(target = "additionalImageUrls", ignore = true)
    @Mapping(target = "accidents", ignore = true)
    @Mapping(target = "mileages", ignore = true)
    @Mapping(target = "options", ignore = true)
    @Mapping(target = "ownershipChanges", ignore = true)
    @Mapping(target = "usage", ignore = true)
    CarDetailResponseDto toCarDetailResponseDto(CarListing carListing);

    /** 사고이력, 사고당 수리이력을 합쳐 Dto로 변환합니다. */
    default List<CarAccidentWithRepairsDto> toCarAccidentWithRepairsDtos(
            List<CarAccident> accidents,
            Map<Long, List<CarAccidentRepair>> repairsByAccidentId
    ) {
        if (accidents == null) {
            return null;
        }

        return accidents.stream()
                .map(accident -> {
                    CarAccidentWithRepairsDto dto = toCarAccidentWithRepairsDto(accident);

                    // 사고별 수리이력 추가
                    List<CarAccidentRepair> repairs = repairsByAccidentId.getOrDefault(accident.getId(), List.of());
                    dto.setRepairs(toCarAccidentRepairDtos(repairs));

                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Mapping(target = "repairs", ignore = true)
    CarAccidentWithRepairsDto toCarAccidentWithRepairsDto(CarAccident accident);
    List<CarAccidentRepairDto> toCarAccidentRepairDtos(List<CarAccidentRepair> repairs);

    List<CarMileageDto> toCarMileageDtos(List<CarMileage> mileages);

    @Mapping(target = "carId", ignore = true)
    CarMileageDto toCarMileageDto(CarMileage mileage);

    List<CarOptionDto> toCarOptionDtos(List<CarOption> options);
    @Mapping(target = "carId", ignore = true)
    CarOptionDto toCarOptionDto(CarOption option);

    List<CarOwnershipChangeDto> toCarOwnershipChangeDtos(List<CarOwnershipChange> ownershipChanges);
    @Mapping(target = "carId", ignore = true)
    CarOwnershipChangeDto toCarOwnershipChangeDto(CarOwnershipChange ownershipChange);

    @Mapping(target = "carId", ignore = true)
    CarUsageDto toCarUsageDto(CarUsage usage);

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "user.id", target = "sellerId")
    UpdateCarListingResponseDto toUpdateCarListingResponseDto(CarListing carListing);
}
