package com.yangsunkue.suncar.repository.car;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.repository.support.Querydsl4RepositorySupport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CarListing Custom Repository 구현체입니다.
 */
@Repository
public class CarListingRepositoryCustomImpl extends Querydsl4RepositorySupport implements CarListingRepositoryCustom {

    public CarListingRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(queryFactory, CarListing.class);
    }

    /**
     * 모든 판매중인 자동차 정보를 찾습니다.
     */
    @Override
    public List<CarListResponseDto> getCarList() {

        /** 사용할 Q타입 객체 선언 */
        QCarListing carListing = QCarListing.carListing;
        QCar car = QCar.car;
        QModel model = QModel.model;
        QCarMileage carMileage = QCarMileage.carMileage;
        QCarListingImage carListingImage = QCarListingImage.carListingImage;

        /** 쿼리 제작 */
        List<Tuple> results = getQueryFactory()

            /** SELECT */
            .select(
                    carListing.id,
                    carListing.price,
                    car.carName,
                    car.fuelType,
                    car.year,
                    car.month,
                    model.brandName.stringValue(),
                    carMileage.distance,
                    carListingImage.imageUrl,
                    carListingImage.isPrimary
            )

            /** FROM */
            .from(carListing)

            /**
             * LEFT JOIN을 사용하면, 해당 테이블 데이터가 없더라도 결과에 포함시킬 수 있다.
             *
             * 해당 데이터가 없어도 포함시켜야 한다면, LEFT JOIN을 사용
             * 해당 데이터가 없으면 제외해야 하거나/무조건 있는 상황이라면 INNER JOIN 사용
             * 아래는 무조건 있는 상황이기 때문에 INNER JOIN
             */
            /** INNER JOIN */
            .join(car).on(carListing.car.id.eq(car.id))
            .join(model).on(car.model.id.eq(model.id))
            .join(carMileage).on(car.id.eq(carMileage.car.id))
            .join(carListingImage).on(carListing.id.eq(carListingImage.carListing.id))

            /** 가장 최근 주행거리 기록을 선택 */
            .where(carMileage.id.in(
                    JPAExpressions
                            .select(carMileage.id.max())
                            .from(carMileage)
                            .groupBy(carMileage.car.id)
            ))
            .fetch();

        /** carListingId 기준으로 그룹화, DTO로 변환 */
        Map<Long, CarListResponseDto> dtoMap = new HashMap<>();

        for (Tuple tuple : results) {

            /** 쿼리결과에서 listing.id, imageUrl과 대표이미지 여부 가져오기 */
            Long carListingId = tuple.get(0, Long.class);
            String imageUrl = tuple.get(8, String.class);
            Boolean isPrimary = tuple.get(9, Boolean.class);

            /** 해당 carListingId의 DTO가 없다면 새로 생성 */
            if (!dtoMap.containsKey(carListingId)) {
                CarListResponseDto dto = CarListResponseDto.builder()
                        .carListingId(carListingId)
                        .price(tuple.get(1, BigDecimal.class))
                        .carName(tuple.get(2, String.class))
                        .fuelType(tuple.get(3, String.class))
                        .year(tuple.get(4, Integer.class))
                        .month(tuple.get(5, Integer.class))
                        .brandName(tuple.get(6, String.class))
                        .distance(tuple.get(7, Integer.class))
                        .build();

                /** 만들어진 dto를 dtoMap에 넣기 */
                dtoMap.put(carListingId, dto);
            }

            /** 대표이미지 여부에 따라 dto 필드에 추가 */
            CarListResponseDto dto = dtoMap.get(carListingId);
            if (isPrimary) {
                dto.setMainImageUrl(imageUrl);
            }
            else {
                /** List의 참조를 get 하기에 add로 값이 변경된다 */
                dto.getOtherImageUrls().add(imageUrl);
            }
        }

        /** 만들어진 dto 리스트를 반환 */
        return new ArrayList<>(dtoMap.values());
    }
}


