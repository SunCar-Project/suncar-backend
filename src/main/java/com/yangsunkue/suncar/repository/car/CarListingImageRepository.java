package com.yangsunkue.suncar.repository.car;

import com.yangsunkue.suncar.entity.car.CarListingImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarListingImageRepository extends JpaRepository<CarListingImage, Long> {

    /** 특정 판매글에 메인 이미지가 있는지 조회 */
    Optional<CarListingImage> findByIdAndIsPrimaryTrue(Long id);
}
