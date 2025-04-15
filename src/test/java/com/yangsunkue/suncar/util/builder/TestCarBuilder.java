package com.yangsunkue.suncar.util.builder;

import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.util.factory.TestCarFactory;
import jakarta.persistence.EntityManager;

/**
 * 테스트 코드에서 사용할 Car 관련 엔티티 영속화를 도와주는 빌더 클래스 입니다.
 * UserBuilder를 포함합니다.
 */
public class TestCarBuilder extends TestBaseBuilder {

    private final TestUserBuilder testUserBuilder;

    public TestCarBuilder(EntityManager em) {
        super(em);
        this.testUserBuilder = new TestUserBuilder(em);
    }

    /** 통합 빌더에서 사용되는 생성자 */
    public TestCarBuilder(EntityManager em, TestUserBuilder testUserBuilder) {
        super(em);
        this.testUserBuilder = testUserBuilder;
    }

    public Model createAndPersistAndFlushModel() {
        Model model = TestCarFactory.createModel().toBuilder().id(null).build();
        persistAndFlush(model);
        return model;
    }

    public Car createAndPersistAndFlushCar() {
        Model model = createAndPersistAndFlushModel();
        return createAndPersistAndFlushCar(model);
    }

    public Car createAndPersistAndFlushCar(Model model) {
        Car car = TestCarFactory.createCar().toBuilder()
                .id(null)
                .model(model)
                .build();
        persistAndFlush(car);
        return car;
    }

    public CarAccident createAndPersistAndFlushCarAccident() {
        Car car = createAndPersistAndFlushCar();
        return createAndPersistAndFlushCarAccident(car);
    }

    public CarAccident createAndPersistAndFlushCarAccident(Car car) {
        CarAccident carAccident = TestCarFactory.createCarAccident().toBuilder()
                .id(null)
                .car(car)
                .build();
        persistAndFlush(carAccident);
        return carAccident;
    }

    public CarAccidentRepair createAndPersistAndFlushCarAccidentRepair() {
        CarAccident carAccident = createAndPersistAndFlushCarAccident();
        return createAndPersistAndFlushCarAccidentRepair(carAccident);
    }

    public CarAccidentRepair createAndPersistAndFlushCarAccidentRepair(CarAccident carAccident) {
        CarAccidentRepair carAccidentRepair = TestCarFactory.createCarAccidentRepair().toBuilder()
                .id(null)
                .carAccident(carAccident)
                .build();
        persistAndFlush(carAccidentRepair);
        return carAccidentRepair;
    }

    public CarListing createAndPersistAndFlushCarListing() {
        Car car = createAndPersistAndFlushCar();
        User user = testUserBuilder.createAndPersistAndFlushUser();
        return createAndPersistAndFlushCarListing(car, user);
    }

    public CarListing createAndPersistAndFlushCarListing(Car car, User user) {
        CarListing carListing = TestCarFactory.createCarListing().toBuilder()
                .id(null)
                .car(car)
                .user(user)
                .build();
        persistAndFlush(carListing);
        return carListing;
    }

    /**
     * @param isPrimary - 메인/일반 이미지를 결정합니다.
     *                  true
     *                      imageUrl: "테스트메인이미지URL",
     *                      isPrimary: true
     *                  false
     *                      imageUrl: "테스트이미지URL",
     *                      isPrimary: false
     */
    public CarListingImage createAndPersistAndFlushCarListingImageByIsPrimary(Boolean isPrimary) {
        CarListing carListing = createAndPersistAndFlushCarListing();
        return createAndPersistAndFlushCarListingImageByIsPrimary(isPrimary, carListing);
    }

    /**
     * @param isPrimary - 메인/일반 이미지를 결정합니다.
     *                  true
     *                      imageUrl: "테스트메인이미지URL",
     *                      isPrimary: true
     *                  false
     *                      imageUrl: "테스트이미지URL",
     *                      isPrimary: false
     */
    public CarListingImage createAndPersistAndFlushCarListingImageByIsPrimary(Boolean isPrimary, CarListing carListing) {
        CarListingImage carListingImage = TestCarFactory.createCarListingImageByIsPrimary(isPrimary).toBuilder()
                .id(null)
                .carListing(carListing)
                .build();
        persistAndFlush(carListingImage);
        return carListingImage;
    }

    public CarMileage createAndPersistAndFlushCarMileage() {
        Car car = createAndPersistAndFlushCar();
        return createAndPersistAndFlushCarMileage(car);
    }

    public CarMileage createAndPersistAndFlushCarMileage(Car car) {
        CarMileage carMileage = TestCarFactory.createCarMileage().toBuilder()
                .id(null)
                .car(car)
                .build();
        persistAndFlush(carMileage);
        return carMileage;
    }

    public CarOption createAndPersistAndFlushCarOption() {
        Car car = createAndPersistAndFlushCar();
        return createAndPersistAndFlushCarOption(car);
    }

    public CarOption createAndPersistAndFlushCarOption(Car car) {
        CarOption carOption = TestCarFactory.createCarOption().toBuilder()
                .id(null)
                .car(car)
                .build();
        persistAndFlush(carOption);
        return carOption;
    }

    public CarOwnershipChange createAndPersistAndFlushCarOwnershipChange() {
        Car car = createAndPersistAndFlushCar();
        return createAndPersistAndFlushCarOwnershipChange(car);
    }

    public CarOwnershipChange createAndPersistAndFlushCarOwnershipChange(Car car) {
        CarOwnershipChange carOwnershipChange = TestCarFactory.createCarOwnershipChange().toBuilder()
                .id(null)
                .car(car)
                .build();
        persistAndFlush(carOwnershipChange);
        return carOwnershipChange;
    }

    public CarUsage createAndPersistAndFlushCarUsage() {
        Car car = createAndPersistAndFlushCar();
        return createAndPersistAndFlushCarUsage(car);
    }

    public CarUsage createAndPersistAndFlushCarUsage(Car car) {
        CarUsage carUsage = TestCarFactory.createCarUsage().toBuilder()
                .id(null)
                .car(car)
                .build();
        persistAndFlush(carUsage);
        return carUsage;
    }
}
