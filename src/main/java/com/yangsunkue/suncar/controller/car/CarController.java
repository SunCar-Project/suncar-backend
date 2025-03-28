package com.yangsunkue.suncar.controller.car;

import com.yangsunkue.suncar.common.constant.ResponseMessages;
import com.yangsunkue.suncar.dto.ResponseDto;
import com.yangsunkue.suncar.dto.car.CarListResponseDto;
import com.yangsunkue.suncar.dto.car.RegisterCarResponseDto;
import com.yangsunkue.suncar.service.facade.CarManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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

    @Qualifier("CarManagementServiceDummyImpl")
    private final CarManagementService carManagementService;

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    @GetMapping("")
    @Operation(summary = "판매 차량 목록 조회")
    public ResponseDto<List<CarListResponseDto>> getCarList() {
        List<CarListResponseDto> carList = carManagementService.getCarList();
        return ResponseDto.of(ResponseMessages.CAR_LIST_RETRIEVED, carList);
    }

    /**
     * 차량을 판매등록합니다.
     */
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "bearer-jwt")
    @Operation(summary = "차량 판매 등록")
    public ResponseEntity<ResponseDto<RegisterCarResponseDto>> registerCar(
            @RequestPart("mainImage") MultipartFile mainImage,
            @RequestPart("additionalImages") List<MultipartFile> additionalImages,
            @RequestPart("carNumber") String carNumber,
            @RequestPart("price") BigDecimal price
    ) {
            RegisterCarResponseDto registeredCar = carManagementService.registerCar(
                    mainImage,
                    additionalImages,
                    carNumber,
                    price
            );
            ResponseDto<RegisterCarResponseDto> response = ResponseDto.of(ResponseMessages.CAR_REGISTERED, registeredCar);

            /**
             * TODO
             * 등록된 차량 상세조회 페이지 리턴해주기
             */
            return ResponseEntity.created(URI.create("/cars"))
                    .body(response);
    }

    /**
     * 차량을 판매등록합니다. - 더미 데이터 입력용 컨트롤러 입니다.
     */

}
















