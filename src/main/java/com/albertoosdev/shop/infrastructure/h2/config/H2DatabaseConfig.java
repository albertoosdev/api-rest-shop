package com.albertoosdev.shop.infrastructure.h2.config;

import com.albertoosdev.shop.domain.port.PriceRepositoryPort;
import com.albertoosdev.shop.infrastructure.h2.mapper.PriceMapper;
import com.albertoosdev.shop.infrastructure.h2.repository.PriceJpaRepository;
import com.albertoosdev.shop.infrastructure.h2.repository.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up H2 database related beans.
 */
@Configuration
public class H2DatabaseConfig {

    /**
     * Creates a PriceRepositoryPort bean that provides the implementation of the price repository.
     *
     * @param jpaRepository the JPA repository for price entities
     * @param priceMapper   the mapper for converting between entity and domain models
     * @return a PriceRepositoryPort implementation
     */
    @Bean
    PriceRepositoryPort priceRepositoryPort(final PriceJpaRepository jpaRepository, final PriceMapper priceMapper) {
        return new PriceRepository(jpaRepository, priceMapper);
    }
}
