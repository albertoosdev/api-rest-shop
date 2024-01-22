package com.albertoosdev.shop.infrastructure.h2.config;

import com.albertoosdev.shop.domain.port.PriceRepositoryPort;
import com.albertoosdev.shop.infrastructure.h2.mapper.PriceMapper;
import com.albertoosdev.shop.infrastructure.h2.repository.PriceJpaRepository;
import com.albertoosdev.shop.infrastructure.h2.repository.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration beans.
 */
@Configuration
public class H2DatabaseConfig {

    /**
     * Define bean
     *
     * @param jpaRepository PriceJpaRepository
     * @param priceMapper   PriceMapper
     * @return the implementation
     */
    @Bean
    PriceRepositoryPort priceRepositoryPort(final PriceJpaRepository jpaRepository, final PriceMapper priceMapper) {
        return new PriceRepository(jpaRepository, priceMapper);
    }
}
