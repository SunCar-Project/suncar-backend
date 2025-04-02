package com.yangsunkue.suncar.service.facade;

import com.yangsunkue.suncar.dto.car.request.RegisterCarDummyRequestDto;
import com.yangsunkue.suncar.dto.car.response.RegisterCarResponseDto;

/**
 * 차량 관련 Facade 서비스 입니다.
 * - 더미 데이터 입력용 입니다.
 */
public interface CarFacadeDummyService extends CarFacadeBaseService {

    /**
     * 차량을 판매등록합니다. - 더미 데이터 입력을 위한 메서드 입니다.
     */
    RegisterCarResponseDto registerCar(RegisterCarDummyRequestDto dto, String userId);
}
