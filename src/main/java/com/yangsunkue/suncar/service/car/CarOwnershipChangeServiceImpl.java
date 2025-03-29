package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarOwnershipChangeDto;
import com.yangsunkue.suncar.entity.car.CarOwnershipChange;
import com.yangsunkue.suncar.repository.car.CarOwnershipChangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CarOwnershipChangeService 엔티티 관련 서비스 클래스 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarOwnershipChangeServiceImpl implements CarOwnershipChangeService {

    private final CarOwnershipChangeRepository carOwnershipChangeRepository;

    /**
     * 차량 번호/소유자 변경이력 정보를 다수 생성합니다.
     */
    @Override
    @Transactional
    public List<CarOwnershipChange> createChanges(List<CarOwnershipChangeDto> dtos) {

        /** 모든 DTO를 엔티티로 변환 */
        List<CarOwnershipChange> carOwnershipChanges = dtos.stream()
                .map(CarOwnershipChangeDto::toEntity)
                .collect(Collectors.toList());

        /** DB에 저장 후 리턴 */
        List<CarOwnershipChange> savedChanges = carOwnershipChangeRepository.saveAll(carOwnershipChanges);

        return savedChanges;
    }
}
