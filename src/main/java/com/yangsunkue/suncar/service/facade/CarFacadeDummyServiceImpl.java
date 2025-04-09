package com.yangsunkue.suncar.service.facade;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.car.*;
import com.yangsunkue.suncar.dto.car.request.RegisterCarDummyRequestDto;
import com.yangsunkue.suncar.dto.car.response.CarDetailResponseDto;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.car.response.RegisterCarResponseDto;
import com.yangsunkue.suncar.dto.repository.CarDetailFetchResult;
import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.NotFoundException;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.CarListingRepository;
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
    private final CarListingRepository carListingRepository;

    /** Car 관련 서비스 */
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
     * 판매 차량 상세정보를 조회합니다.
     * QueryDSL을 사용하여 데이터를 가져온 후, 서비스에서 매퍼를 통해 DTO로 변환됩니다.
     *
     * @param listingId 차량 판매등록 ID
     */
    @Override
    public CarDetailResponseDto getCarDetail(Long listingId) {

        /** 차량 상세정보 엔티티들 조회 */
        CarDetailFetchResult data = carListingRepository.getCarDetailById(listingId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.CAR_LISTING_NOT_FOUND));

        /** DTO로 변환 */
        CarDetailResponseDto carDetail = carMapper.toCarDetailResponseDto(data.carListing());

        /** 나머지 데이터 매핑 */
        processImages(data.images(), carDetail);
        carDetail.setAccidents(carMapper.toCarAccidentWithRepairsDtos(data.accidents(), data.repairsByAccidentId()));
        carDetail.setMileages(carMapper.toCarMileageDtos(data.mileages()));
        carDetail.setOptions(carMapper.toCarOptionDtos(data.options()));
        carDetail.setOwnershipChanges(carMapper.toCarOwnershipChangeDtos(data.ownershipChanges()));
        if (data.usage() != null) {
            carDetail.setUsage(carMapper.toCarUsageDto(data.usage()));
        }

        return carDetail;
    }

    /**
     * 차량을 판매등록합니다.
     * - 더미 데이터를 이용합니다.
     *
     * @param userId - 판매자 아이디
     */
    @Override
    @Transactional
    public RegisterCarResponseDto registerCar(RegisterCarDummyRequestDto dto, String userId) {

        /**
         * 각 엔티티마다 더미 데이터 생성 후 DB에 저장
         */

        /** Model */
        ModelDto modelDto = carDummyDataGenerator.generateModelDto();
        Model model = modelService.createModel(modelDto);

        /** Car */
        CarDto carDto = carDummyDataGenerator.generateCarDto(model.getId(), dto.getCarNumber());
        Car car = carService.createCar(carDto);

        /** CarAccident */
        List<CarAccidentDto> accidentDtos = carDummyDataGenerator.generateCarAccidentDtos(car.getId());
        List<CarAccident> accidents = carAccidentService.createAccidents(accidentDtos);

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
         * CarAccidentRepair
         * - Accident.id를 추출해 인자로 전달
         */
        List<Long> accidentIds = new ArrayList<>();
        for (CarAccident accident : accidents) {
            accidentIds.add(accident.getId());
        }

        List<CarAccidentRepairDto> accidentRepairDtos = carDummyDataGenerator.generateCarAccidentRepairDtos(accidentIds);
        carAccidentRepairService.createAccidentRepairs(accidentRepairDtos);

        /** CarMileage */
        List<CarMileageDto> mileageDtos = carDummyDataGenerator.generateCarMileageDtos(car.getId());
        carMileageService.createMileages(mileageDtos);

        /** CarOwnershipChange */
        List<CarOwnershipChangeDto> ownershipChangeDtos = carDummyDataGenerator.generateCarOwnershipChangeDtos(car.getId());
        carOwnershipChangeService.createChanges(ownershipChangeDtos);

        /** CarUsage */
        CarUsageDto usageDto = carDummyDataGenerator.generateCarUsageDto(car.getId());
        carUsageService.createUsage(usageDto);

        /** CarOption */
        List<CarOptionDto> optionDtos = carDummyDataGenerator.generateCarOptionDtos(car.getId());
        carOptionService.createOptions(optionDtos);

        /** CarListingImage */
        CarListingImageDto mainImageDto = carDummyDataGenerator.generateCarListingImageDtoFromMainImage(listing.getId(), dto.getMainImage());
        carListingImageService.createMainImage(mainImageDto);

        List<CarListingImageDto> additionalImageDtos = carDummyDataGenerator.generateCarListingDtosFromAdditionalImages(listing.getId(), dto.getAdditionalImages());
        carListingImageService.createImages(additionalImageDtos);

        /**
         * 결과를 dto로 만든 후 리턴
         */
        RegisterCarResponseDto registerCar = carMapper.toRegisterCarResponseDto(listing, car, model);
        return registerCar;
    }

    /**
     * 이미지 데이터를 받아 메인/일반 이미지로 구분하여 dto에 담습니다.
     */
    private void processImages(List<CarListingImage> images, CarDetailResponseDto dto) {

        String mainImageUrl = null;
        List<String> additionalImageUrls = new ArrayList<>();

        for (CarListingImage image : images) {
            if (image.getIsPrimary()) {
                mainImageUrl = image.getImageUrl();
            }
            else {
                additionalImageUrls.add(image.getImageUrl());
            }
        }

        dto.setMainImageUrl(mainImageUrl);
        dto.setAdditionalImageUrls(additionalImageUrls);
    }
}
