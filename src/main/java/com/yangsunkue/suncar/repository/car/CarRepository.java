package com.yangsunkue.suncar.repository.car;

import com.yangsunkue.suncar.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
