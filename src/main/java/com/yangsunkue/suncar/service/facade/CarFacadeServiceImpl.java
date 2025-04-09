package com.yangsunkue.suncar.service.facade;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.car.response.CarDetailResponseDto;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.car.response.RegisterCarResponseDto;
import com.yangsunkue.suncar.dto.repository.CarDetailFetchResult;
import com.yangsunkue.suncar.entity.car.CarListingImage;
import com.yangsunkue.suncar.exception.NotFoundException;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.CarListingRepository;
import com.yangsunkue.suncar.service.car.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 차량 관련 Facade 서비스 구현체 입니다.
 */
@Service
@Profile("!dev")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarFacadeServiceImpl implements CarFacadeService {

    private final CarMapper carMapper;
    private final CarListingRepository carListingRepository;
    private final CarListingService carListingService;

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
     *
     * @param mainImage - 메인 이미지
     * @param additionalImages - 나머지 이미지 리스트
     * @param carNumber - 차량번호
     * @param price - 차량 가격
     */
    @Override
    @Transactional
    public RegisterCarResponseDto registerCar(
            MultipartFile mainImage,
            List<MultipartFile> additionalImages,
            String carNumber,
            BigDecimal price
    ) {
        /**
         * TODO - 이미지 저장 로직 만들기
         * 1. 메인 이미지, 나머지 이미지를 S3에 등록
         * 2. DB에 이미지 경로 저장
         */

        /**
         * TODO - 카히스토리 API
         * 1. 차량번호로 카히스토리 API 호출
         * 2. 각 서비스 함수로 값 전달
         */

        return RegisterCarResponseDto.builder().build();
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
