package com.yangsunkue.suncar.repository.car;

import com.yangsunkue.suncar.config.TestJpaConfig;
import com.yangsunkue.suncar.config.TestQuerydslConfig;
import com.yangsunkue.suncar.dto.CarAndUserTestEntities;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.repository.CarDetailFetchResult;
import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.util.TestQueryUtils;
import com.yangsunkue.suncar.util.builder.TestIntegrationBuilder;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import({
        CarListingRepositoryCustomImpl.class,
        TestQuerydslConfig.class,
        TestJpaConfig.class,
        TestIntegrationBuilder.class
})
class CarListingRepositoryCustomImplTest {

    @Autowired
    private CarListingRepository carListingRepository;
    @Autowired
    private EntityManager em;
    @Autowired
    private TestIntegrationBuilder testIntegrationBuilder;

    /** 테스트 데이터 선언 */
    private User testUser;
    private Model testModel;
    private Car testCar;
    private CarAccident testCarAccident;
    private CarAccidentRepair testCarAccidentRepair;
    private CarListing testCarListing;
    private CarListingImage testMainImage;
    private CarListingImage testAdditionalImage;
    private CarMileage testCarMileage;
    private CarOption testCarOption;
    private CarOwnershipChange testCarOwnershipChange;
    private CarUsage testCarUsage;
    private CarListResponseDto testCarListResponseDto;
    private CarDetailFetchResult testCarDetailFetchResult;

    /** 테스트 데이터 초기화 */
    @BeforeEach
    void setup() {

        /** 모든 Car, User 관련 엔티티 DB에 저장하고 가져오기 */
        CarAndUserTestEntities testEntities = testIntegrationBuilder.createAndPersistAndFlushCarAndUserAllTestEntities();
        testUser = testEntities.user();
        testModel = testEntities.model();
        testCar = testEntities.car();
        testCarAccident = testEntities.accident();
        testCarAccidentRepair = testEntities.repair();
        testCarListing = testEntities.carListing();
        testMainImage = testEntities.mainImage();
        testAdditionalImage = testEntities.additionalImage();
        testCarMileage = testEntities.mileage();
        testCarOption = testEntities.option();
        testCarOwnershipChange = testEntities.ownershipChange();
        testCarUsage = testEntities.usage();

        /** testCarListResponseDto */
        List<String> otherImageUrls = new ArrayList<>();
        otherImageUrls.add(testAdditionalImage.getImageUrl());
        testCarListResponseDto = CarListResponseDto.builder()
                .carListingId(testCarListing.getId())
                .price(testCarListing.getPrice())
                .brandName(testModel.getBrandName())
                .carName(testCar.getCarName())
                .fuelType(testCar.getFuelType())
                .year(testCar.getYear())
                .month(testCar.getMonth())
                .distance(testCarMileage.getDistance())
                .mainImageUrl(testMainImage.getImageUrl())
                .otherImageUrls(otherImageUrls)
                .build();

        /** testCarDetailFetchResult */
        testCarDetailFetchResult = new CarDetailFetchResult(
                testCarListing,
                List.of(testMainImage, testAdditionalImage),
                List.of(testCarAccident),
                Map.of(testCarAccident.getId(), List.of(testCarAccidentRepair)),
                List.of(testCarMileage),
                List.of(testCarOption),
                List.of(testCarOwnershipChange),
                testCarUsage
        );

        /** 캐시 및 쿼리 통계 초기화 */
        TestQueryUtils.clearCacheAndResetStatistics(em);
    }

    @Test
    @DisplayName("판매중인 차량 목록 조회 테스트")
    void getCarListTest() {

        // when
        List<CarListResponseDto> result = carListingRepository.getCarList();

        // then
        assertThat(result).isNotEmpty();

        CarListResponseDto dto = result.get(0);
        assertThat(dto.getCarListingId()).isEqualTo(testCarListing.getId());
        assertThat(dto.getPrice()).isEqualByComparingTo(testCarListing.getPrice());
        assertThat(dto)
                .usingRecursiveComparison()
                .withComparatorForType(BigDecimal::compareTo, BigDecimal.class)
                .isEqualTo(testCarListResponseDto);
    }

    @Test
    @DisplayName("판매중인 차량 목록 조회 시 N+1 문제 없이 한 번의 쿼리로 조회되는지 테스트")
    void getCarListFetchedWithSingleQueryTest() {

        // when
        carListingRepository.getCarList();

        // then
        int queryCount = TestQueryUtils.getQueryCount(em);
        assertThat(queryCount).isEqualTo(1);
    }

    @Test
    @DisplayName("판매중인 차량이 없을 경우 빈 ArrayList 반환하는지 테스트")
    void getCarListShouldReturnEmptyArrayListWhenNoDataExistsTest() {

        // given
        em.createQuery("DELETE FROM CarListingImage").executeUpdate();
        em.createQuery("DELETE FROM CarListing").executeUpdate();
        em.flush();
        em.clear();

        // when
        List<CarListResponseDto> result = carListingRepository.getCarList();

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("차량 상세정보 조회 테스트")
    void getCarDetailByIdTest() {

        // when
        Optional<CarDetailFetchResult> optionalResult = carListingRepository.getCarDetailById(testCarListing.getId());

        // then
        assertThat(optionalResult).isPresent();
        CarDetailFetchResult result = optionalResult.get();

        assertThat(result.carListing().getId()).isEqualTo(testCarListing.getId());
        assertThat(result)
                .usingRecursiveComparison()
                .withComparatorForType(BigDecimal::compareTo, BigDecimal.class)
                .withComparatorForType(TestQueryUtils.getDateTimeComparator(), LocalDateTime.class)
                .isEqualTo(testCarDetailFetchResult);
    }

    @Test
    @DisplayName("차량 상세정보 조회 시 의도된 횟수(8회)만큼의 쿼리로 조회되는지 테스트")
    void getCarDetailByIdShouldExecuteExpectedNumberOfQueriesTest() {

        // when
        carListingRepository.getCarDetailById(testCarListing.getId());

        // then
        int queryCount = TestQueryUtils.getQueryCount(em);
        assertThat(queryCount).isEqualTo(8);
    }

    @Test
    @DisplayName("존재하지 않는 차량 상세정보 조회 시 빈 Optional 반환하는지 테스트")
    void getCarDetailByIdNotFoundTest() {

        // when
        Optional<CarDetailFetchResult> result = carListingRepository.getCarDetailById(99999L);

        // then
        assertThat(result).isEmpty();
    }
}