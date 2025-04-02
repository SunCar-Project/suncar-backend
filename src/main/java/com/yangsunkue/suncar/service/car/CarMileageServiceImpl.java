package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarMileageDto;
import com.yangsunkue.suncar.entity.car.CarMileage;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.CarMileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CarMileage 엔티티 관련 서비스 클래스 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarMileageServiceImpl implements CarMileageService {

    private final CarMileageRepository carMileageRepository;
    private final CarMapper carMapper;

    /**
     * 차량 주행거리 정보를 다수 생성합니다.
     */
    @Override
    @Transactional
    public List<CarMileage> createMileages(List<CarMileageDto> dtos) {

        /** 모든 DTO를 엔티티로 변환 */
        List<CarMileage> carMileages = dtos.stream()
                .map(carMapper::fromMileageDto)
                .collect(Collectors.toList());

        /** DB 저장 및 리턴 */
        List<CarMileage> savedMileages = carMileageRepository.saveAll(carMileages);

        return savedMileages;
    }
}
