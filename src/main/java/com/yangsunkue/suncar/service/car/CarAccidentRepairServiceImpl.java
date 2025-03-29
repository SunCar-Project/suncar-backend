package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarAccidentRepairDto;
import com.yangsunkue.suncar.entity.car.CarAccidentRepair;
import com.yangsunkue.suncar.repository.car.CarAccidentRepairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarAccidentRepairServiceImpl implements CarAccidentRepairService {

    private final CarAccidentRepairRepository carAccidentRepairRepository;

    /**
     * 차량 사고이력 상세정보를 다수 생성합니다.
     */
    @Override
    @Transactional
    public List<CarAccidentRepair> createAccidentRepairs(List<CarAccidentRepairDto> dtos) {

        // 모든 DTO를 엔티티로 변환
        List<CarAccidentRepair> carAccidentRepairs = dtos.stream()
                .map(CarAccidentRepairDto::toEntity)
                .collect(Collectors.toList());

        List<CarAccidentRepair> savedRepairs = carAccidentRepairRepository.saveAll(carAccidentRepairs);
        return savedRepairs;
    }
}
