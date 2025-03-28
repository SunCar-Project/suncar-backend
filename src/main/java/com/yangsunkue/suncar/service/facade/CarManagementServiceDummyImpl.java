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
 *
 * -> 차량 판매 등록 더미 데이터 입력용
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarManagementServiceDummyImpl implements CarManagementService {

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
     */
    @Override
    @Transactional
    public RegisterCarResponseDto registerCar(
            MultipartFile mainImage,
            List<MultipartFile> additionalImages,
            String carNumber,
            BigDecimal price
    ) {
        return RegisterCarResponseDto.builder().build();
    }

    /**
     * 차량을 판매등록합니다. - 더미 데이터 입력을 위한 오버로딩 메서드 입니다.
     *
     * @param mainImage - 메인 이미지
     * @param additionalImages - 나머지 이미지들
     * @param carNumber - 차량번호
     * @param price - 가격
     * @return
     */
    @Transactional
    public RegisterCarResponseDto registerCar(
            String mainImage,
            List<String> additionalImages,
            String carNumber,
            BigDecimal price
    ) {

        /**
         * 1. 각 엔티티별 서비스 함수 호출 - 더미 데이터 입력
         * 2. 입력된 데이터들 dto로 제작 ( dto 제작 메서드는 dto클래스 내에 만들기 )
         * 3. 리턴
         */


        return RegisterCarResponseDto.builder().build();
    }
}
