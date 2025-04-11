package com.yangsunkue.suncar.service.facade;

import com.yangsunkue.suncar.common.enums.CarListingStatus;
import com.yangsunkue.suncar.common.enums.UserRole;
import com.yangsunkue.suncar.dto.car.*;
import com.yangsunkue.suncar.dto.car.request.RegisterCarDummyRequestDto;
import com.yangsunkue.suncar.dto.car.response.CarDetailResponseDto;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.car.response.RegisterCarResponseDto;
import com.yangsunkue.suncar.dto.repository.CarDetailFetchResult;
import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.CarListingRepository;
import com.yangsunkue.suncar.repository.user.UserRepository;
import com.yangsunkue.suncar.service.car.*;
import com.yangsunkue.suncar.util.CarDummyDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class) // 이거뭐지
class CarFacadeDummyServiceImplTest {

    /** 실제 테스트 대상이 되는 서비스 객체, Mock객체가 여기에 주입됨 */
    @InjectMocks
    private CarFacadeDummyServiceImpl carFacadeDummyServiceImpl;

    /**
     * InjectMocks가 의존하는 외부 클래스들을 Mock으로 대체
     * 테스트 대상 서비스를 외부 의존성으로부터 격리하기 위함
     */
    @Mock
    private CarDummyDataGenerator carDummyDataGenerator;
    @Mock
    private CarMapper carMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CarListingRepository carListingRepository;
    @Mock
    private ModelService modelService;
    @Mock
    private CarService carService;
    @Mock
    private CarMileageService carMileageService;
    @Mock
    private CarAccidentService carAccidentService;
    @Mock
    private CarAccidentRepairService carAccidentRepairService;
    @Mock
    private CarOwnershipChangeService carOwnershipChangeService;
    @Mock
    private CarUsageService carUsageService;
    @Mock
    private CarOptionService carOptionService;
    @Mock
    private CarListingService carListingService;
    @Mock
    private CarListingImageService carListingImageService;

    /**
     * 테스트 데이터 선언
     */
    private User testUser;
    private Model testModel;
    private Car testCar;
    private CarListing testCarListing;
    private CarMileage testCarMileage;
    private CarUsage testCarUsage;
    private List<CarListResponseDto> testCarListResponseDtos;
    private CarDetailFetchResult testCarDetailFetchResult;
    private CarDetailResponseDto testCarDetailResponseDto;
    private RegisterCarDummyRequestDto testRegisterCarDummyRequestDto;
    private RegisterCarResponseDto testRegisterCarResponseDto;


    /**
     * 테스트 데이터 초기화
     */
    @BeforeEach
    void setUp() {

        /** testUser 객체 생성 */
        testUser = User.builder()
                .id(1L)
                .userId("testUser")
                .email("testUser@test.com")
                .username("테스트사용자")
                .passwordHash("hashedPassword")
                .phoneNumber("01012345678")
                .role(UserRole.회원)
                .build();

        /** testModel 객체 생성 */
        testModel = Model.builder()
                .id(1L)
                .brandName("테스트브랜드")
                .modelName("테스트모델")
                .isForeign(true)
                .build();

        /** testCar 객체 생성 */
        testCar = Car.builder()
                .id(1L)
                .model(testModel)
                .carName("테스트자동차명")
                .carNumber("123가1234")
                .displacement("2998")
                .fuelType("가솔린")
                .year(2025)
                .month(12)
                .bodyShape("왜건")
                .modelType("자가용 승용")
                .firstInsuranceDate(LocalDate.of(2025, 04, 10))
                .identificationNumber("테스트차대번호")
                .minPrice(BigDecimal.valueOf(5000))
                .maxPrice(BigDecimal.valueOf(3500))
                .build();

        /** testCarListing 객체 생성 */
        testCarListing = CarListing.builder()
                .id(1L)
                .car(testCar)
                .user(testUser)
                .price(BigDecimal.valueOf(5000))
                .description("테스트설명")
                .status(CarListingStatus.FOR_SALE)
                .build();

        /** testCarListResponseDtos 객체 생성 */
        List<CarMileage> mileages = new ArrayList<>();
        testCarMileage = CarMileage.builder()
                .id(1L)
                .car(testCar)
                .distance(20000)
                .provider("테스트제공처")
                .recordDate(LocalDate.of(2025, 04, 10))
                .build();
        mileages.add(testCarMileage);

        String mainImageUrl = "테스트메인이미지URL";

        List<String> additionalImageUrls = new ArrayList<>();
        String additionalImageUrl = "테스트이미지URL";
        additionalImageUrls.add(additionalImageUrl);

        testCarListResponseDtos = new ArrayList<>();
        CarListResponseDto carListResponseDto = CarListResponseDto.builder()
                .carListingId(testCarListing.getId())
                .price(testCarListing.getPrice())
                .brandName(testModel.getBrandName())
                .carName(testCar.getCarName())
                .fuelType(testCar.getFuelType())
                .year(testCar.getYear())
                .month(testCar.getMonth())
                .distance(testCarMileage.getDistance())
                .mainImageUrl(mainImageUrl)
                .otherImageUrls(additionalImageUrls)
                .build();
        testCarListResponseDtos.add(carListResponseDto);

        /** testCarDetailFetchResult 객체 생성 */
        List<CarListingImage> images = new ArrayList<>();
        CarListingImage mainImage = CarListingImage.builder()
                .id(1L)
                .carListing(testCarListing)
                .imageUrl(mainImageUrl)
                .isPrimary(true)
                .build();
        CarListingImage additionalImage = CarListingImage.builder()
                .id(2L)
                .carListing(testCarListing)
                .imageUrl(additionalImageUrl)
                .isPrimary(false)
                .build();
        images.add(mainImage);
        images.add(additionalImage);

        testCarUsage = CarUsage.builder()
                .id(1L)
                .car(testCar)
                .rentalHistory("없음")
                .businessHistory("없음")
                .governmentHistory("없음")
                .build();

        List<CarAccident> accidents = new ArrayList<>();
        Map<Long, List<CarAccidentRepair>> repairsByAccidentId = new HashMap<>();
        List<CarOption> options = new ArrayList<>();
        List<CarOwnershipChange> ownershipChanges = new ArrayList<>();

        testCarDetailFetchResult = new CarDetailFetchResult(
                testCarListing,
                images,
                accidents,
                repairsByAccidentId,
                mileages,
                options,
                ownershipChanges,
                testCarUsage
        );


        /** testCarDetailResponseDto 객체 생성 */
        List<CarAccidentWithRepairsDto> accidentWithRepairDtos = new ArrayList<>();

        List<CarMileageDto> mileageDtos = new ArrayList<>();
        CarMileageDto mileageDto = CarMileageDto.builder()
                .carId(testCar.getId())
                .distance(testCarMileage.getDistance())
                .provider(testCarMileage.getProvider())
                .recordDate(testCarMileage.getRecordDate())
                .build();
        mileageDtos.add(mileageDto);

        CarUsageDto usageDto = CarUsageDto.builder()
                .carId(testCar.getId())
                .rentalHistory(testCarUsage.getRentalHistory())
                .businessHistory(testCarUsage.getBusinessHistory())
                .governmentHistory(testCarUsage.getGovernmentHistory())
                .build();

        List<CarOptionDto> optionDtos = new ArrayList<>();
        List<CarOwnershipChangeDto> ownershipChangeDtos = new ArrayList<>();

        testCarDetailResponseDto = CarDetailResponseDto.builder()
                .id(testCarListing.getId())
                .price(testCarListing.getPrice())
                .description(testCarListing.getDescription())
                .status(testCarListing.getStatus())
                .sellerId(testUser.getId())
                .sellerUserName(testUser.getUsername())
                .carId(testCar.getId())
                .carName(testCar.getCarName())
                .carNumber(testCar.getCarNumber())
                .displacement(testCar.getDisplacement())
                .fuelType(testCar.getFuelType())
                .year(testCar.getYear())
                .month(testCar.getMonth())
                .bodyShape(testCar.getBodyShape())
                .modelType(testCar.getModelType())
                .firstInsuranceDate(testCar.getFirstInsuranceDate())
                .identificationNumber(testCar.getIdentificationNumber())
                .minPrice(testCar.getMinPrice())
                .maxPrice(testCar.getMaxPrice())
                .brandName(testModel.getBrandName())
                .modelName(testModel.getModelName())
                .isForeign(testModel.getIsForeign())
                .mainImageUrl(mainImageUrl)
                .additionalImageUrls(additionalImageUrls)
                .accidents(accidentWithRepairDtos)
                .mileages(mileageDtos)
                .options(optionDtos)
                .ownershipChanges(ownershipChangeDtos)
                .usage(usageDto)
                .build();

        /** testRegisterCarDummyRequestDto 객체 생성 */
        testRegisterCarDummyRequestDto = RegisterCarDummyRequestDto.builder()
                .mainImage(mainImageUrl)
                .additionalImages(additionalImageUrls)
                .carNumber(testCar.getCarNumber())
                .price(testCarListing.getPrice())
                .build();

        /** testRegisterCarResponseDto 객체 생성 */
        testRegisterCarResponseDto = RegisterCarResponseDto.builder()
                .listingId(testCarListing.getId())
                .price(testCarListing.getPrice())
                .brandName(testModel.getBrandName())
                .carName(testCar.getCarName())
                .carNumber(testCar.getCarNumber())
                .build();
    }

    @Test
    @DisplayName("판매중인 차량 목록 조회 테스트")
    void getCarListTest() {

        // given
        when(carListingService.getCarList()).thenReturn(testCarListResponseDtos);

        // when
        List<CarListResponseDto> result = carFacadeDummyServiceImpl.getCarList();

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(testCarListResponseDtos.size());
        assertThat(result.get(0).getCarListingId()).isEqualTo(testCarListing.getId());
        assertThat(result.get(0))
                .usingRecursiveComparison()
                .isEqualTo(testCarListResponseDtos.get(0));

        // verify
        verify(carListingService).getCarList();
    }

    @Test
    @DisplayName("판매 차량 상세정보 조회 테스트")
    void getCarDetailTest() {

        // given
        Long listingId = 1L;
        when(carListingRepository.getCarDetailById(listingId)).thenReturn(Optional.of(testCarDetailFetchResult));
        when(carMapper.toCarDetailResponseDto(any(CarListing.class))).thenReturn(testCarDetailResponseDto);

        // when
        CarDetailResponseDto result = carFacadeDummyServiceImpl.getCarDetail(listingId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(testCarListing.getId());
        assertThat(result.getCarId()).isEqualTo(testCar.getId());
        assertThat(result.getSellerId()).isEqualTo(testUser.getId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(testCarDetailResponseDto);

        // verify
        verify(carListingRepository).getCarDetailById(listingId);
        verify(carMapper).toCarDetailResponseDto(any(CarListing.class));
    }

    @Test
    @DisplayName("차량 판매등록 테스트 - 더미 데이터")
    void registerCarTest() {

        // given
        String userId = testUser.getUserId();

        /** 테스트에 필요한 기본 객체 생성 */
        ModelDto modelDto = ModelDto.builder().build();
        CarDto carDto = CarDto.builder().build();
        CarListingDto carListingDto = CarListingDto.builder().build();
        CarUsageDto usageDto = CarUsageDto.builder().build();
        CarUsage usage = CarUsage.builder().build();
        CarListingImageDto mainImageDto = CarListingImageDto.builder().build();
        CarListingImage mainImage = CarListingImage.builder().build();
        List<CarAccidentDto> accidentDtos = new ArrayList<>();
        List<CarAccident> accidents = new ArrayList<>();
        List<CarAccidentRepairDto> accidentRepairDtos = new ArrayList<>();
        List<CarAccidentRepair> accidentRepairs = new ArrayList<>();
        List<CarMileageDto> mileageDtos = new ArrayList<>();
        List<CarMileage> mileages = new ArrayList<>();
        List<CarOwnershipChangeDto> ownershipChangeDtos = new ArrayList<>();
        List<CarOwnershipChange> ownershipChanges = new ArrayList<>();
        List<CarOptionDto> optionDtos = new ArrayList<>();
        List<CarOption> options = new ArrayList<>();
        List<CarListingImageDto> additionalImageDtos = new ArrayList<>();
        List<CarListingImage> additionalImages = new ArrayList<>();

        /** 메서드 모킹 */
        when(carDummyDataGenerator.generateModelDto()).thenReturn(modelDto);
        when(modelService.createModel(any(ModelDto.class))).thenReturn(testModel);

        when(carDummyDataGenerator.generateCarDto(anyLong(), any(String.class))).thenReturn(carDto);
        when(carService.createCar(any(CarDto.class))).thenReturn(testCar);

        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(testUser));

        when(carDummyDataGenerator.generateCarListingDto(anyLong(), anyLong(), any(BigDecimal.class))).thenReturn(carListingDto);
        when(carListingService.createListing(any(CarListingDto.class))).thenReturn(testCarListing);

        when(carDummyDataGenerator.generateCarAccidentDtos(anyLong())).thenReturn(accidentDtos);
        when(carAccidentService.createAccidents(anyList())).thenReturn(accidents);

        when(carDummyDataGenerator.generateCarAccidentRepairDtos(anyList())).thenReturn(accidentRepairDtos);
        when(carAccidentRepairService.createAccidentRepairs(anyList())).thenReturn(accidentRepairs);

        when(carDummyDataGenerator.generateCarMileageDtos(anyLong())).thenReturn(mileageDtos);
        when(carMileageService.createMileages(anyList())).thenReturn(mileages);

        when(carDummyDataGenerator.generateCarOwnershipChangeDtos(anyLong())).thenReturn(ownershipChangeDtos);
        when(carOwnershipChangeService.createChanges(anyList())).thenReturn(ownershipChanges);

        when(carDummyDataGenerator.generateCarUsageDto(anyLong())).thenReturn(usageDto);
        when(carUsageService.createUsage(any(CarUsageDto.class))).thenReturn(usage);

        when(carDummyDataGenerator.generateCarOptionDtos(anyLong())).thenReturn(optionDtos);
        when(carOptionService.createOptions(anyList())).thenReturn(options);

        when(carDummyDataGenerator.generateCarListingImageDtoFromMainImage(anyLong(), any(String.class))).thenReturn(mainImageDto);
        when(carListingImageService.createMainImage(any(CarListingImageDto.class))).thenReturn(mainImage);

        when(carDummyDataGenerator.generateCarListingDtosFromAdditionalImages(anyLong(), anyList())).thenReturn(additionalImageDtos);
        when(carListingImageService.createImages(anyList())).thenReturn(additionalImages);

        when(carMapper.toRegisterCarResponseDto(any(CarListing.class), any(Car.class), any(Model.class))).thenReturn(testRegisterCarResponseDto);

        // when
        RegisterCarResponseDto result = carFacadeDummyServiceImpl.registerCar(testRegisterCarDummyRequestDto, userId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getListingId()).isEqualTo(testCarListing.getId());
        assertThat(result.getPrice()).isEqualTo(testCarListing.getPrice());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(testRegisterCarResponseDto);

        // verify
        verify(userRepository).findByUserId(userId);
        verify(carMapper).toRegisterCarResponseDto(any(CarListing.class), any(Car.class), any(Model.class));
    }
}