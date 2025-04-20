package com.yangsunkue.suncar.controller.car;

import com.yangsunkue.suncar.common.constant.ResponseMessages;
import com.yangsunkue.suncar.dto.ResponseDto;
import com.yangsunkue.suncar.dto.car.request.UpdateCarListingRequestDto;
import com.yangsunkue.suncar.dto.car.request.RegisterCarDummyRequestDto;
import com.yangsunkue.suncar.dto.car.response.CarDetailResponseDto;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.car.response.RegisterCarResponseDto;
import com.yangsunkue.suncar.dto.car.response.UpdateCarListingResponseDto;
import com.yangsunkue.suncar.entity.car.CarListing;
import com.yangsunkue.suncar.security.CustomUserDetails;
import com.yangsunkue.suncar.service.car.CarListingService;
import com.yangsunkue.suncar.service.car.CarListingServiceImpl;
import com.yangsunkue.suncar.service.facade.CarFacadeDummyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * 차량 관련 컨트롤러 입니다.
 */
@RestController
@RequestMapping("/cars")
@ResponseStatus(HttpStatus.OK)
@Tag(name = "Car")
@RequiredArgsConstructor
public class CarController {

    /**
     * 더미 데이터 입력용 구현체
     *
     * TODO
     * 카히스토리 API 및 S3 도입 후, CarFacadeService 로 교체
     */
    private final CarFacadeDummyService carFacadeDummyService;
    private final CarListingService carListingService;

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    @GetMapping("")
    @Operation(summary = "판매 차량 목록 조회")
    public ResponseDto<List<CarListResponseDto>> getCarList() {
        List<CarListResponseDto> carList = carFacadeDummyService.getCarList();
        return ResponseDto.of(ResponseMessages.CAR_LIST_RETRIEVED, carList);
    }

    /**
     * 현재 사용자가 판매중인 차량 목록을 조회합니다.
     */
    @GetMapping("/me")
    @SecurityRequirement(name = "bearer-jwt")
    @Operation(summary = "현재 사용자의 판매 차량 목록 조회")
    public ResponseDto<List<CarListResponseDto>> getMyCarList(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        List<CarListResponseDto> myCarList = carListingService.getCarListBySellerId(userDetails.getId());
        return ResponseDto.of(ResponseMessages.MY_CAR_LIST_RETRIEVED, myCarList);
    }

    /**
     * 판매 차량 상세정보를 조회합니다.
     */
    @GetMapping("/{listingId}")
    @Operation(summary = "판매 차량 상세정보 조회")
    public ResponseDto<CarDetailResponseDto> getCarDetail(@PathVariable Long listingId) {
        CarDetailResponseDto carDetail = carFacadeDummyService.getCarDetailById(listingId);
        return ResponseDto.of(ResponseMessages.CAR_DETAIL_RETRIEVED, carDetail);
    }

    /**
     * 차량을 판매등록합니다. - 배포용
     */
//    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @SecurityRequirement(name = "bearer-jwt")
//    @Operation(summary = "차량 판매 등록")
//    public ResponseEntity<ResponseDto<RegisterCarResponseDto>> registerCar(
//            @RequestPart("mainImage") MultipartFile mainImage,
//            @RequestPart("additionalImages") List<MultipartFile> additionalImages,
//            @RequestPart("carNumber") String carNumber,
//            @RequestPart("price") BigDecimal price
//    ) {
//            RegisterCarResponseDto registeredCar = carFacadeService.registerCar(
//                    mainImage,
//                    additionalImages,
//                    carNumber,
//                    price
//            );
//            ResponseDto<RegisterCarResponseDto> response = ResponseDto.of(ResponseMessages.CAR_REGISTERED, registeredCar);
//
//            /**
//             * TODO
//             * 등록된 차량 상세조회 페이지 리턴해주기
//             */
//            return ResponseEntity.created(URI.create("/cars"))
//                    .body(response);
//    }

    /**
     * 차량을 판매등록합니다. - 더미 데이터 입력용 컨트롤러 입니다.
     */
    @PostMapping("/dummy")
    @SecurityRequirement(name = "bearer-jwt")
    @Operation(summary = "차량 판매 등록 - 더미 데이터 입력용")
    public ResponseEntity<ResponseDto<RegisterCarResponseDto>> registerCarDummy(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody RegisterCarDummyRequestDto dto
    ) {
        RegisterCarResponseDto registeredCar = carFacadeDummyService.registerCar(dto, userDetails.getId());
        ResponseDto<RegisterCarResponseDto> response = ResponseDto.of(ResponseMessages.CAR_REGISTERED, registeredCar);

        return ResponseEntity.created(URI.create("/cars/" + registeredCar.getListingId()))
                .body(response);
    }

    /**
     * 판매중인 차량 가격, 설명을 수정합니다.
     * 본인이 등록한 차량만 수정할 수 있습니다.
     */
    @PatchMapping("/{listingId}")
    @SecurityRequirement(name = "bearer-jwt")
    @Operation(summary = "판매 차량 상세정보 수정")
    public ResponseDto<UpdateCarListingResponseDto> updateCarDetail(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long listingId,
            @RequestBody UpdateCarListingRequestDto dto
    ) {
        UpdateCarListingResponseDto updated = carListingService.updatePriceAndDesc(listingId, userDetails.getId(), dto);
        return ResponseDto.of(ResponseMessages.CAR_DETAIL_UPDATED, updated);
    }

    /**
     * 판매중인 차량 정보를 모두 삭제합니다.
     * 본인이 등록한 차량만 삭제할 수 있습니다.
     */
    @DeleteMapping("/{listingId}")
    @SecurityRequirement(name = "bearer-jwt")
    @Operation(summary = "판매 차량 삭제")
    public ResponseDto deleteCarListing(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long listingId
    ) {
        carListingService.softDeleteCarListingWithRelatedEntities(listingId, userDetails.getId());
        return ResponseDto.of(ResponseMessages.CAR_LISTING_DELETED);
    }
}
