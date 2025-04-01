package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarAccidentDto;
import com.yangsunkue.suncar.entity.car.CarAccident;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.CarAccidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CarAccident 엔티티 관련 서비스 클래스 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarAccidentServiceImpl implements CarAccidentService {

    private final CarAccidentRepository carAccidentRepository;
    private final CarMapper carMapper;

    /**
     * 차량 사고이력을 다수 생성합니다.
     */
    @Override
    @Transactional
    public List<CarAccident> createAccidents(List<CarAccidentDto> dtos) {

        // 모든 DTO를 엔티티로 변환
        List<CarAccident> carAccidents = dtos.stream()
                .map(carMapper::fromAccidentDto)
                .collect(Collectors.toList());

        // DB에 저장하고 리턴
        List<CarAccident> savedAccidents = carAccidentRepository.saveAll(carAccidents);
        return savedAccidents;
    }
}
