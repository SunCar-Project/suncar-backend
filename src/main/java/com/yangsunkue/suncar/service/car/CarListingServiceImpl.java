package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarListingDto;
import com.yangsunkue.suncar.entity.car.CarListing;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.CarListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CarListing 엔티티 관련 서비스 클래스 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarListingServiceImpl implements CarListingService {

    private final CarListingRepository carListingRepository;
    private final CarMapper carMapper;

    /**
     * 차량 판매등록 정보를 생성합니다.
     */
    @Override
    @Transactional
    public CarListing createListing(CarListingDto dto) {
        CarListing carListing = carMapper.fromListingDto(dto);
        CarListing saved = carListingRepository.save(carListing);

        return saved;
    }
}