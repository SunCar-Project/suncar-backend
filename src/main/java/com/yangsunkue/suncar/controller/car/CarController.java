package com.yangsunkue.suncar.controller.car;

import com.yangsunkue.suncar.common.constant.ResponseMessages;
import com.yangsunkue.suncar.dto.ResponseDto;
import com.yangsunkue.suncar.dto.car.request.RegisterCarDummyRequestDto;
import com.yangsunkue.suncar.dto.car.response.CarDetailResponseDto;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.car.response.RegisterCarResponseDto;
import com.yangsunkue.suncar.security.CustomUserDetails;
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
        RegisterCarResponseDto registeredCar = carFacadeDummyService.registerCar(dto, userDetails.getUserId());
        ResponseDto<RegisterCarResponseDto> response = ResponseDto.of(ResponseMessages.CAR_REGISTERED, registeredCar);

        return ResponseEntity.created(URI.create("/cars/" + registeredCar.getListingId()))
                .body(response);
    }

    /**
     * 판매 차량 상세정보를 조회합니다.
     */
    @GetMapping("/{listingId}")
    @Operation(summary = "판매 차량 상세정보 조회")
    public ResponseDto<CarDetailResponseDto> getCarDetail(@PathVariable Long listingId) {
        CarDetailResponseDto carDetail = carFacadeDummyService.getCarDetail(listingId);
        return ResponseDto.of(ResponseMessages.CAR_DETAIL_RETRIEVED, carDetail);
    }
}
