package com.yangsunkue.suncar.repository.car;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.repository.CarDetailFetchResult;
import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.QUser;
import com.yangsunkue.suncar.repository.support.Querydsl4RepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
                    model.brandName,
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

    /**
     * 차량 상세정보를 조회합니다.
     */
    @Override
    public Optional<CarDetailFetchResult> getCarDetailById(Long listingId) {

        QUser user = QUser.user;
        QCar car = QCar.car;
        QCarAccident carAccident = QCarAccident.carAccident;
        QCarAccidentRepair carAccidentRepair = QCarAccidentRepair.carAccidentRepair;
        QCarListing carListing = QCarListing.carListing;
        QCarListingImage carListingImage = QCarListingImage.carListingImage;
        QCarMileage carMileage = QCarMileage.carMileage;
        QCarOption carOption = QCarOption.carOption;
        QCarOwnershipChange carOwnershipChange = QCarOwnershipChange.carOwnershipChange;
        QCarUsage carUsage = QCarUsage.carUsage;
        QModel model = QModel.model;

        /** 1. 기본 차량 정보 조회 (CarListing, Car, Model, User) */
        CarListing result = getQueryFactory()
                .selectFrom(carListing)
                .join(carListing.car, car).fetchJoin()
                .join(car.model, model).fetchJoin()
                .join(carListing.user, user).fetchJoin()
                .where(carListing.id.eq(listingId))
                .fetchOne();

        if (result == null) {
            return Optional.empty();
        }
        Long carId = result.getCar().getId();


        /** 2. 모든 이미지 정보 조회 */
        List<CarListingImage> images = getQueryFactory()
                .selectFrom(carListingImage)
                .where(carListingImage.carListing.id.eq(listingId))
                .fetch();

        /** 3. 사고 정보와 수리 정보 조회 */
        // 사고이력 전부 가져오기
        List<CarAccident> accidents = getQueryFactory()
                .selectFrom(carAccident)
                .where(carAccident.car.id.eq(carId))
                .fetch();

        // 사고이력 id 리스트 생성
        List<Long> accidentIds = accidents.stream()
                .map(CarAccident::getId)
                .collect(Collectors.toList());

        // 사고이력들의 수리정보 전부 조회
        List<CarAccidentRepair> repairs = accidentIds.isEmpty()
                ? List.of()
                : getQueryFactory()
                    .selectFrom(carAccidentRepair)
                    .where(carAccidentRepair.carAccident.id.in(accidentIds))
                    .fetch();

        // 수리정보를 사고이력 id 기준으로 그룹핑 리스트화
        Map<Long, List<CarAccidentRepair>> repairsByAccidentId = repairs.stream()
                .collect(Collectors.groupingBy(repair -> repair.getCarAccident().getId()));

        /** 4. 주행거리 조회 */
        List<CarMileage> mileages = getQueryFactory()
                .selectFrom(carMileage)
                .where(carMileage.car.id.eq(carId))
                .orderBy(carMileage.recordDate.desc())
                .fetch();

        /** 5. 옵션/안전장치 조회 */
        List<CarOption> options = getQueryFactory()
                .selectFrom(carOption)
                .where(carOption.car.id.eq(carId))
                .fetch();

        /** 6. 소유자/번호 변경이력 조회 */
        List<CarOwnershipChange> ownershipChanges = getQueryFactory()
                .selectFrom(carOwnershipChange)
                .where(carOwnershipChange.car.id.eq(carId))
                .orderBy(carOwnershipChange.changeDate.desc())
                .fetch();

        /** 7. 사용이력 조회 */
        CarUsage usage = getQueryFactory()
                .selectFrom(carUsage)
                .where(carUsage.car.id.eq(carId))
                .fetchOne();

        /** 결과 엔티티 리턴 */
        return Optional.of(new CarDetailFetchResult(
                result,
                images,
                accidents,
                repairsByAccidentId,
                mileages,
                options,
                ownershipChanges,
                usage
        ));
    }

    /**
     * 판매중인 차량을 soft delete 방식으로 삭제합니다.
     * CarListing 엔티티를 시작으로, 연관된 모든 엔티티를 함께 삭제합니다.
     */
    @Override
    @Transactional
    public void softDeleteCarListingWithRelatedEntities(Long listingId) {

        QCarListing carListing = QCarListing.carListing;
        QCarListingImage carListingImage = QCarListingImage.carListingImage;
        QCar car = QCar.car;
        QCarAccident carAccident = QCarAccident.carAccident;
        QCarAccidentRepair carAccidentRepair = QCarAccidentRepair.carAccidentRepair;
        QCarMileage carMileage = QCarMileage.carMileage;
        QCarOption carOption = QCarOption.carOption;
        QCarOwnershipChange carOwnershipChange = QCarOwnershipChange.carOwnershipChange;
        QCarUsage carUsage = QCarUsage.carUsage;

        /** 0. car.id 가져오기 */
        Long carId = getQueryFactory()
                .select(carListing.car.id)
                .from(carListing)
                .where(carListing.id.eq(listingId)
                        .and(carListing.isDeleted.eq(false)))
                .fetchOne();

        if (carId == null) {
            return;
        }

        /** 1. CarListingImage 삭제 */
        getQueryFactory()
                .update(carListingImage)
                .set(carListingImage.isDeleted, true)
                .where(carListingImage.carListing.id.eq(listingId)
                        .and(carListingImage.isDeleted.eq(false)))
                .execute();

        /** 2. CarAccidentRepair 삭제 */
        getQueryFactory()
                .update(carAccidentRepair)
                .set(carAccidentRepair.isDeleted, true)
                .where(carAccidentRepair.carAccident.id.in(
                        JPAExpressions
                                .select(carAccident.id)
                                .from(carAccident)
                                .where(carAccident.car.id.eq(carId)
                                        .and(carAccident.isDeleted.eq(false)))
                        )
                        .and(carAccidentRepair.isDeleted.eq(false)))
                .execute();

        /** 3. CarAccident 삭제 */
        getQueryFactory()
                .update(carAccident)
                .set(carAccident.isDeleted, true)
                .where(carAccident.car.id.eq(carId)
                        .and(carAccident.isDeleted.eq(false)))
                .execute();

        /** 4. CarMileage 삭제 */
        getQueryFactory()
                .update(carMileage)
                .set(carMileage.isDeleted, true)
                .where(carMileage.car.id.eq(carId)
                        .and(carMileage.isDeleted.eq(false)))
                .execute();

        /** 5. CarOption 삭제 */
        getQueryFactory()
                .update(carOption)
                .set(carOption.isDeleted, true)
                .where(carOption.car.id.eq(carId)
                        .and(carOption.isDeleted.eq(false)))
                .execute();

        /** 6. CarOwnershipChange 삭제 */
        getQueryFactory()
                .update(carOwnershipChange)
                .set(carOwnershipChange.isDeleted, true)
                .where(carOwnershipChange.car.id.eq(carId)
                        .and(carOwnershipChange.isDeleted.eq(false)))
                .execute();

        /** 7. CarUsage 삭제 */
        getQueryFactory()
                .update(carUsage)
                .set(carUsage.isDeleted, true)
                .where(carUsage.car.id.eq(carId)
                        .and(carUsage.isDeleted.eq(false)))
                .execute();

        /** 8. CarListing 삭제 */
        getQueryFactory()
                .update(carListing)
                .set(carListing.isDeleted, true)
                .where(carListing.id.eq(listingId)
                        .and(carListing.isDeleted.eq(false)))
                .execute();

        /** 9. Car 삭제 */
        getQueryFactory()
                .update(car)
                .set(car.isDeleted, true)
                .where(car.id.eq(carId)
                        .and(car.isDeleted.eq(false)))
                .execute();
    }
}
