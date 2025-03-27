package com.yangsunkue.suncar.controller.car;

import com.yangsunkue.suncar.common.constant.ResponseMessages;
import com.yangsunkue.suncar.dto.ResponseDto;
import com.yangsunkue.suncar.dto.car.CarListResponseDto;
import com.yangsunkue.suncar.service.car.CarService;
import com.yangsunkue.suncar.service.car.CarServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    private final CarService carService;

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    @GetMapping("")
    @Operation(summary = "판매 차량 목록 조회")
    public ResponseDto<List<CarListResponseDto>> getCarList() {
        List<CarListResponseDto> carList = carService.getCarList();
        return ResponseDto.of(ResponseMessages.CAR_LIST_RETRIEVED, carList);
    }
}
















