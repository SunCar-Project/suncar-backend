package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.common.enums.BrandName;
import com.yangsunkue.suncar.dto.car.CarListResponseDto;
import com.yangsunkue.suncar.repository.car.CarListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private final CarListingRepository carListingRepository;

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    public List<CarListResponseDto> getCarList() {
        List<CarListResponseDto> carList = carListingRepository.getCarList();

        /** BrandName enum을 value로 변경 */
        carList.forEach(dto -> {
            BrandName brandNameEnum = BrandName.valueOf(dto.getBrandName());
            dto.setBrandName(brandNameEnum.getValue());
        });

        return carList;
    }
}
