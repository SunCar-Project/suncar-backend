package com.yangsunkue.suncar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화 -> EntityListener, CreatedDate, LastModifiedDate 를 사용하기 위함
@Configuration
public class JpaConfig {
}
