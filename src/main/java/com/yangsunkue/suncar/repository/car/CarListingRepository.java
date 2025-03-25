package com.yangsunkue.suncar.repository.car;

import com.yangsunkue.suncar.entity.car.CarListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarListingRepository extends JpaRepository<CarListing, Long>, CarListingRepositoryCustom {
}
