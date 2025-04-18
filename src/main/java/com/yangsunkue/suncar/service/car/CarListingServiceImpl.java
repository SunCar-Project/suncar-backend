package com.yangsunkue.suncar.service.car;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.car.CarListingDto;
import com.yangsunkue.suncar.dto.car.request.UpdateCarListingRequestDto;
import com.yangsunkue.suncar.dto.car.response.CarListResponseDto;
import com.yangsunkue.suncar.dto.car.response.UpdateCarListingResponseDto;
import com.yangsunkue.suncar.entity.car.CarListing;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.ForbiddenException;
import com.yangsunkue.suncar.exception.NotFoundException;
import com.yangsunkue.suncar.mapper.CarMapper;
import com.yangsunkue.suncar.repository.car.CarListingRepository;
import com.yangsunkue.suncar.repository.user.UserRepository;
import com.yangsunkue.suncar.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CarListing 엔티티 관련 서비스 클래스 입니다.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarListingServiceImpl implements CarListingService {

    private final CarListingRepository carListingRepository;
    private final CarMapper carMapper;

    /**
     * 판매중인 차량 목록을 조회합니다.
     */
    @Override
    public List<CarListResponseDto> getCarList() {
        List<CarListResponseDto> carList = carListingRepository.getCarList();
        return carList;
    }

    /**
     * 특정 판매자가 판매하는 차량 목록을 조회합니다.
     */
    @Override
    public List<CarListResponseDto> getCarListBySellerId(Long sellerId) {
        List<CarListResponseDto> carList = carListingRepository.getCarList(sellerId);
        return carList;
    }

    /**
     * 차량 판매등록 정보를 생성합니다.
     */
    @Override
    @Transactional
    public CarListing createListing(CarListingDto dto) {
        CarListing carListing = carMapper.fromListingDto(dto);
        CarListing saved = carListingRepository.save(carListing);

        return saved;
    }

    /**
     * 판매중인 차량 가격, 설명을 수정합니다.
     * 본인이 등록한 차량만 수정할 수 있습니다.
     *
     * @param listingId 차량 판매등록 ID
     * @param userId 수정 요청자 로그인 아이디
     * @param dto 수정 요청 Dto
     */
    @Override
    @Transactional
    public UpdateCarListingResponseDto updatePriceAndDesc(
            Long listingId,
            String userId,
            UpdateCarListingRequestDto dto
    ) {

        /** 수정할 차량 찾기  */
        CarListing target = carListingRepository.findById(listingId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.CAR_LISTING_NOT_FOUND));

        /** 본인의 차량인지 확인 */
        if (!target.getUser().getUserId().equals(userId)) {
            throw new ForbiddenException(ErrorMessages.NOT_OWNER_OF_CAR_LISTING);
        }

        /** 업데이트 */
        target.patch(dto);

        /** Dto로 변환 후 리턴 */
        UpdateCarListingResponseDto updated = carMapper.toUpdateCarListingResponseDto(target);
        return updated;
    }

    /**
     * 판매중인 차량과 관련 엔티티를 전부 삭제합니다.( soft delete )
     * 본인이 등록한 차량만 삭제할 수 있습니다.
     *
     * @param listingId 차량 판매등록 ID
     * @param userId 삭제 요청자 로그인 아이디
     */
    @Override
    @Transactional
    public void softDeleteCarListingWithRelatedEntities(
            Long listingId,
            String userId
    ) {

        /** 수정할 차량 찾기  */
        CarListing target = carListingRepository.findById(listingId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.CAR_LISTING_NOT_FOUND));

        /** 본인의 차량인지 확인 */
        if (!target.getUser().getUserId().equals(userId)) {
            throw new ForbiddenException(ErrorMessages.NOT_OWNER_OF_CAR_LISTING);
        }

        /** 판매중인 차량과 관련 엔티티 전부 삭제 */
        carListingRepository.softDeleteCarListingWithRelatedEntities(listingId);
    }
}