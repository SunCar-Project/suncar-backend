package com.yangsunkue.suncar.service.facade;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.car.*;
import com.yangsunkue.suncar.dto.car.request.RegisterCarDummyRequestDto;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.car.response.RegisterCarResponseDto;
import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.NotFoundException;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.user.UserRepository;
import com.yangsunkue.suncar.service.car.*;
import com.yangsunkue.suncar.util.CarDummyDataGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 차량 관련 Facade 서비스 구현체 입니다.
 * - 더미 데이터 입력용으로, 개발환경일 때만 활성화됩니다.
 */
@Service
@Profile("dev")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarFacadeDummyServiceImpl implements CarFacadeDummyService {

    private final CarDummyDataGenerator carDummyDataGenerator;
    private final CarMapper carMapper;

    private final UserRepository userRepository;

    /** Car 관련 Repository */
    private final ModelService modelService;
    private final CarService carService;
    private final CarMileageService carMileageService;
    private final CarAccidentService carAccidentService;
    private final CarAccidentRepairService carAccidentRepairService;
    private final CarOwnershipChangeService carOwnershipChangeService;
    private final CarUsageService carUsageService;
    private final CarOptionService carOptionService;
    private final CarListingService carListingService;
    private final CarListingImageService carListingImageService;

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    @Override
    public List<CarListResponseDto> getCarList() {
        List<CarListResponseDto> carList = carListingService.getCarList();
        return carList;
    }


    /**
     * 차량을 판매등록합니다.
     * - 더미 데이터를 이용합니다.
     */
    @Override
    @Transactional
    public RegisterCarResponseDto registerCar(RegisterCarDummyRequestDto dto, String userId) {
        /**
         * 1. 각 엔티티별 서비스 함수 호출 - 더미 데이터 입력
         * 2. 입력된 데이터들 dto로 제작 ( dto 제작 메서드는 dto클래스 내에 만들기 )
         * 3. 리턴
         */
        /** Model */
        ModelDto modelDto = carDummyDataGenerator.generateModelDto();
        Model model = modelService.createModel(modelDto);

        /** Car */
        CarDto carDto = carDummyDataGenerator.generateCarDto(model.getId(), dto.getCarNumber());
        Car car = carService.createCar(carDto);

        /** CarMileage */
        List<CarMileageDto> mileageDtos = carDummyDataGenerator.generateCarMileageDtos(car.getId());
        List<CarMileage> mileages = carMileageService.createMileages(mileageDtos);

        /** CarAccident */
        List<CarAccidentDto> accidentDtos = carDummyDataGenerator.generateCarAccidentDtos(car.getId());
        List<CarAccident> accidents = carAccidentService.createAccidents(accidentDtos);

        /**
         * CarAccidentRepair
         * - Accident.id를 추출해 인자로 전달
         */
        List<Long> accidentIds = new ArrayList<>();
        for (CarAccident accident : accidents) {
            accidentIds.add(accident.getId());
        }

        List<CarAccidentRepairDto> accidentRepairDtos = carDummyDataGenerator.generateCarAccidentRepairDtos(accidentIds);
        List<CarAccidentRepair> accidentRepairs = carAccidentRepairService.createAccidentRepairs(accidentRepairDtos);

        /** CarOwnershipChange */
        List<CarOwnershipChangeDto> ownershipChangeDtos = carDummyDataGenerator.generateCarOwnershipChangeDtos(car.getId());
        List<CarOwnershipChange> ownershipChanges = carOwnershipChangeService.createChanges(ownershipChangeDtos);

        /** CarUsage */
        List<CarUsageDto> usageDtos = carDummyDataGenerator.generateCarUsageDtos(car.getId());
        List<CarUsage> usages = carUsageService.createUsages(usageDtos);

        /** CarOption */
        List<CarOptionDto> optionDtos = carDummyDataGenerator.generateCarOptionDtos(car.getId());
        List<CarOption> options = carOptionService.createOptions(optionDtos);

        /**
         * CarListing
         * - 차량 등록 요청자 id를 인자로 전달
         */
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND));
        Long userPrimaryId = user.getId();

        CarListingDto listingDto = carDummyDataGenerator.generateCarListingDto(car.getId(), userPrimaryId, dto.getPrice());
        CarListing listing = carListingService.createListing(listingDto);

        /**
         * CarListingImage
         */
        CarListingImageDto mainImageDto = carDummyDataGenerator.generateCarListingImageDtoFromMainImage(listing.getId(), dto.getMainImage());
        CarListingImage mainImage = carListingImageService.createMainImage(mainImageDto);

        List<CarListingImageDto> additionalImageDtos = carDummyDataGenerator.generateCarListingDtosFromAdditionalImages(listing.getId(), dto.getAdditionalImages());
        List<CarListingImage> additionalImages = carListingImageService.createImages(additionalImageDtos);

        /**
         * 결과를 dto로 만든 후 리턴
         */
        RegisterCarResponseDto registerCar = carMapper.toRegisterCarResponseDto(listing, car, model);
        return registerCar;
    }
}
