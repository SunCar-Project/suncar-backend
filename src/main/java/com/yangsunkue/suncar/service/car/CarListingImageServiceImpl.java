package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.car.CarListingImageDto;
import com.yangsunkue.suncar.entity.car.CarListingImage;
import com.yangsunkue.suncar.exception.DuplicateResourceException;
import com.yangsunkue.suncar.exception.InvalidArgumentException;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.CarListingImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CarListingImage 엔티티 관련 서비스 클래스 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarListingImageServiceImpl implements CarListingImageService {

    private final CarListingImageRepository carListingImageRepository;
    private final CarMapper carMapper;

    /**
     * 차량 판매등록 메인 이미지를 생성합니다.
     */
    @Override
    @Transactional
    public CarListingImage createMainImage(CarListingImageDto dto) {

        System.out.println(dto.getListingId());

        // 이미 메인 이미지가 있는지 확인
        Optional<CarListingImage> existingMainImage = carListingImageRepository.findByCarListingIdAndIsPrimaryTrue(dto.getListingId());
        existingMainImage.ifPresent(image -> {
            throw new DuplicateResourceException(ErrorMessages.DUPLICATE_MAIN_IMAGE);
        });

        // 요청받은 이미지가 메인 이미지인지 확인 ( isPrimary 확인 )
        if (!dto.getIsPrimary()) {
            throw new InvalidArgumentException(ErrorMessages.IMAGE_NOT_PRIMARY);
        }

        // 엔티티 변환 후 DB 저장 및 리턴
        CarListingImage carListingImageEntity = carMapper.fromListingImageDto(dto);
        CarListingImage saved = carListingImageRepository.save(carListingImageEntity);

        return saved;
    }


    /**
     * 차량 판매등록 이미지를 다수 생성합니다.
     */
    @Override
    @Transactional
    public List<CarListingImage> createImages(List<CarListingImageDto> dtos) {

        // 요청받은 이미지들이 일반 이미지인지 확인 ( isPrimary 확인 )
        boolean hasPrimaryImage = dtos.stream()
                .anyMatch(CarListingImageDto::getIsPrimary);

        if (hasPrimaryImage) {
            throw new InvalidArgumentException(ErrorMessages.PRIMARY_IMAGES_NOT_ALLOWED);
        }

        // 모든 DTO를 엔티티로 변환 후 DB 저장 및 리턴
        List<CarListingImage> carListingImages = dtos.stream()
                .map(carMapper::fromListingImageDto)
                .collect(Collectors.toList());

        List<CarListingImage> savedImages = carListingImageRepository.saveAll(carListingImages);

        return savedImages;
    }


    /**
     * TODO
     * 메인 이미지 변경 메서드 만들기
     */
}
