package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.ModelDto;
import com.yangsunkue.suncar.entity.car.Model;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Model 엔티티 관련 서비스 클래스 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final CarMapper carMapper;

    /**
     * 차량 모델/브랜드 정보를 생성합니다.
     */
    @Override
    @Transactional
    public Model createModel(ModelDto dto) {
        Model model = carMapper.fromModelDto(dto);
        Model saved = modelRepository.save(model);
        return saved;
    }
}
