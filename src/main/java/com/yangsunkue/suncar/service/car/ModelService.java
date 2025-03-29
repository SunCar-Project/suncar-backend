package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.ModelDto;
import com.yangsunkue.suncar.entity.car.Model;

/**
 * Model 엔티티 관련 서비스 인터페이스 입니다.
 */
public interface ModelService {

    /**
     * 차량 모델/브랜드 정보를 생성합니다.
     */
    Model createModel(ModelDto dto);
}
