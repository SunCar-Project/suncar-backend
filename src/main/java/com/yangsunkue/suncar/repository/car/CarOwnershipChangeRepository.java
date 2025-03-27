package com.yangsunkue.suncar.repository.car;

import com.yangsunkue.suncar.entity.car.CarOwnershipChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarOwnershipChangeRepository extends JpaRepository<CarOwnershipChange, Long> {
}
