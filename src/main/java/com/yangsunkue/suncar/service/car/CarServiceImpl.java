package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.dto.car.CarDto;
import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.repository.car.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Car 엔티티 관련 서비스 클래스 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    /**
     * 차량 정보를 생성합니다.
     */
    @Override
    @Transactional
    public Car createCar(CarDto dto) {
        Car car = CarDto.toEntity(dto);
        Car saved = carRepository.save(car);

        return saved;
    }
}
