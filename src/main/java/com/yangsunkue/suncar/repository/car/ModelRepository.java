package com.yangsunkue.suncar.repository.car;

import com.yangsunkue.suncar.entity.car.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
