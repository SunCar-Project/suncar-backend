package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarListingImageDto;
import com.yangsunkue.suncar.entity.car.CarListingImage;

import java.util.List;

/**
 * CarListingImage 엔티티 관련 서비스 인터페이스 입니다.
 */
public interface CarListingImageService {

    /**
     * 차량 판매등록 메인 이미지를 생성합니다.
     */
    CarListingImage createMainImage(CarListingImageDto dto);

    /**
     * 차량 판매등록 이미지를 다수 생성합니다.
     */
    List<CarListingImage> createImages(List<CarListingImageDto> dtos);
}
