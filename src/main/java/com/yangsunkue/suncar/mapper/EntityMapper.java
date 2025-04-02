package com.yangsunkue.suncar.mapper;

import com.yangsunkue.suncar.entity.car.*;
import com.yangsunkue.suncar.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface EntityMapper {
    default Model toModel(Long id) {
        return id == null ? null : Model.builder().id(id).build();
    }

    default Car toCar(Long id) {
        return id == null ? null : Car.builder().id(id).build();
    }

    default CarAccident toCarAccident(Long id) {
        return id == null ? null : CarAccident.builder().id(id).build();
    }

    default CarAccidentRepair toCarAccidentRepair(Long id) {
        return id == null ? null : CarAccidentRepair.builder().id(id).build();
    }

    default CarListing toCarListing(Long id) {
        return id == null ? null : CarListing.builder().id(id).build();
    }

    default User toUser(Long id) {
        return id == null ? null : User.builder().id(id).build();
    }

    default CarListingImage toCarListingImage(Long id) {
        return id == null ? null : CarListingImage.builder().id(id).build();
    }

    default CarMileage toCarMileage(Long id) {
        return id == null ? null : CarMileage.builder().id(id).build();
    }

    default CarOption toCarOption(Long id) {
        return id == null ? null : CarOption.builder().id(id).build();
    }

    default CarOwnershipChange toCarOwnershipChange(Long id) {
        return id == null ? null : CarOwnershipChange.builder().id(id).build();
    }

    default CarUsage toCarUsage(Long id) {
        return id == null ? null : CarUsage.builder().id(id).build();
    }
}