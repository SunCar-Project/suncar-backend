package com.yangsunkue.suncar.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * 매퍼 관련 설정 클래스 입니다.
 * - 매핑되지 않은 필드에 대한 경고를 무시하도록 합니다.
 */
@MapperConfig(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BaseMapperConfig {
}
