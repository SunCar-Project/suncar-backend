package com.yangsunkue.suncar.service.car;


import com.yangsunkue.suncar.dto.car.CarUsageDto;
import com.yangsunkue.suncar.entity.car.CarUsage;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.CarUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CarUsage 엔티티 관련 서비스 클래스 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarUsageServiceImpl implements CarUsageService {

    private final CarUsageRepository carUsageRepository;
    private final CarMapper carMapper;

    /**
     * 차량 사용이력 정보를 생성합니다.
     */
    @Override
    @Transactional
    public CarUsage createUsage(CarUsageDto dto) {

        CarUsage usage = carMapper.fromUsageDto(dto);
        CarUsage saved = carUsageRepository.save(usage);
        return saved;
    }
}
