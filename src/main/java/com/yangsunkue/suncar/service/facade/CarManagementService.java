package com.yangsunkue.suncar.service.facade;

import com.yangsunkue.suncar.dto.car.CarListResponseDto;
import com.yangsunkue.suncar.dto.car.RegisterCarResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * 차량 관련 Facade 서비스 입니다.
 */
public interface CarManagementService {

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    List<CarListResponseDto> getCarList();


    /**
     * 차량을 판매등록합니다.
     */
    RegisterCarResponseDto registerCar(
            MultipartFile mainImage,
            List<MultipartFile> additionalImages,
            String carNumber,
            BigDecimal price
    );
}
