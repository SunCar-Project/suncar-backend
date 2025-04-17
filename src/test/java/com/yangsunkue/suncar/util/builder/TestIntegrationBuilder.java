package com.yangsunkue.suncar.util.builder;

import com.yangsunkue.suncar.dto.CarAndUserTestEntities;
import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.User;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

/**
 * 테스트 코드에서 사용할 엔티티 영속화를 도와주는 통합 빌더 클래스 입니다.
 * 모든 빌더 클래스를 포함합니다.
 */
@Component
public class TestIntegrationBuilder extends TestBaseBuilder {

    private final TestUserBuilder testUserBuilder;
    private final TestCarBuilder testCarBuilder;

    public TestIntegrationBuilder(EntityManager em) {
        super(em);
        this.testUserBuilder = new TestUserBuilder(em);
        this.testCarBuilder = new TestCarBuilder(em, testUserBuilder);
    }

    /**
     * 판매 차량(CarListing)과 관련된 모든 테스트 엔티티를 DB에 등록하고 리턴합니다.
     */
    public CarAndUserTestEntities createAndPersistAndFlushCarAndUserAllTestEntities() {
        User user = testUserBuilder.createAndPersistAndFlushUser();
        Model model = testCarBuilder.createAndPersistAndFlushModel();
        Car car = testCarBuilder.createAndPersistAndFlushCar(model);
        CarListing carListing = testCarBuilder.createAndPersistAndFlushCarListing(car, user);
        CarAccident accident = testCarBuilder.createAndPersistAndFlushCarAccident(car);
        CarAccidentRepair repair = testCarBuilder.createAndPersistAndFlushCarAccidentRepair(accident);
        CarListingImage mainImage = testCarBuilder.createAndPersistAndFlushCarListingImageByIsPrimary(true, carListing);
        CarListingImage additionalImage = testCarBuilder.createAndPersistAndFlushCarListingImageByIsPrimary(false, carListing);
        CarMileage mileage = testCarBuilder.createAndPersistAndFlushCarMileage(car);
        CarOption option = testCarBuilder.createAndPersistAndFlushCarOption(car);
        CarOwnershipChange ownershipChange = testCarBuilder.createAndPersistAndFlushCarOwnershipChange(car);
        CarUsage usage = testCarBuilder.createAndPersistAndFlushCarUsage(car);

        em.clear();

        return new CarAndUserTestEntities(
                user,
                model,
                car,
                carListing,
                accident,
                repair,
                mainImage,
                additionalImage,
                mileage,
                option,
                ownershipChange,
                usage
        );
    }

    /** 빌더 접근자 메서드들 */
    public TestUserBuilder getTestUserBuilder() {
        return testUserBuilder;
    }

    public TestCarBuilder getTestCarBuilder() {
        return testCarBuilder;
    }
}
