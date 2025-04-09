package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarOptionDto;
import com.yangsunkue.suncar.entity.car.CarOption;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.CarOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CarOption 엔티티 관련 서비스 클래스 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarOptionServiceImpl implements CarOptionService {

    private final CarOptionRepository carOptionRepository;
    private final CarMapper carMapper;

    @Override
    @Transactional
    /**
     * 차량 안전장치/옵션 정보를 다수 생성합니다.
     */
    public List<CarOption> createOptions (List<CarOptionDto> dtos) {

        /** 모든 DTO를 엔티티로 변환 */
        List<CarOption> carOptions = dtos.stream()
                .map(carMapper::fromOptionDto)
                .collect(Collectors.toList());

        /** DB 저장 및 리턴 */
        List<CarOption> savedOptions = carOptionRepository.saveAll(carOptions);

        return savedOptions;
    }
}
