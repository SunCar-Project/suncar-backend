package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarDto;
import com.yangsunkue.suncar.entity.car.Car;

/**
 * Car 엔티티 관련 서비스 클래스 입니다.
 */
public interface CarService {

    /**
     * 차량 정보(Car)를 생성합니다.
     */
    public Car createCar(CarDto dto);
}
