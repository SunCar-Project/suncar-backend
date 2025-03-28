package com.yangsunkue.suncar.service.facade;

import com.yangsunkue.suncar.common.enums.BrandName;
import com.yangsunkue.suncar.dto.car.CarListResponseDto;
import com.yangsunkue.suncar.dto.car.RegisterCarResponseDto;
import com.yangsunkue.suncar.repository.car.CarListingRepository;
import com.yangsunkue.suncar.service.facade.CarManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * 차량 관련 Facade 서비스 구현체 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarManagementServiceImpl implements CarManagementService {

    private final CarListingRepository carListingRepository;

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    @Override
    public List<CarListResponseDto> getCarList() {
        List<CarListResponseDto> carList = carListingRepository.getCarList();

        /** BrandName enum을 value로 변경 */
        carList.forEach(dto -> {
            BrandName brandNameEnum = BrandName.valueOf(dto.getBrandName());
            dto.setBrandName(brandNameEnum.getValue());
        });

        return carList;
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
}
