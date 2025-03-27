package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

/**
 * 판매 등록된 차량의 이미지를 관리하는 엔티티입니다.
 */
@Entity
@Table(name = "car_listing_image")
@SQLDelete(sql = "UPDATE car_listing_image SET is_deleted = true WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CarListingImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", nullable = false)
    private CarListing carListing;

    @Column(name = "image_url", length = 2000, nullable = false)
    private String imageUrl;

    /** 대표 이미지 여부 */
    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary;
}























