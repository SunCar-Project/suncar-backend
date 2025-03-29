package com.yangsunkue.suncar.service.car;


import com.yangsunkue.suncar.dto.car.CarUsageDto;
import com.yangsunkue.suncar.entity.car.CarUsage;
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

    /**
     * 차량 사용이력 정보를 다수 생성합니다.
     */
    @Override
    @Transactional
    public List<CarUsage> createUsages(List<CarUsageDto> dtos) {

        /** 모든 DTO를 엔티티로 변환 */
        List<CarUsage> carUsages = dtos.stream()
                .map(CarUsageDto::toEntity)
                .collect(Collectors.toList());

        /** DB에 저장 후 리턴 */
        List<CarUsage> savedUsages = carUsageRepository.saveAll(carUsages);

        return savedUsages;
    }
}
