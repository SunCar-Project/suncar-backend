package com.yangsunkue.suncar.repository.car;

import com.yangsunkue.suncar.entity.car.CarMileage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarMileageRepository extends JpaRepository<CarMileage, Long> {
}
