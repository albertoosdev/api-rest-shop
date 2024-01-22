package com.albertoosdev.shop.infrastructure.spring.config;

import com.albertoosdev.shop.domain.port.FindPriceByParamUseCasePort;
import com.albertoosdev.shop.domain.port.PriceRepositoryPort;
import com.albertoosdev.shop.usecase.FindPriceByParamUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration beans.
 */
@Configuration
public class UseCaseConfig {

    /**
     * Define bean
     *
     * @param priceRepositoryPort   priceRepositoryPort
     * @return the implementation
     */
    @Bean
    FindPriceByParamUseCasePort findPriceByParamUseCasePort(final PriceRepositoryPort priceRepositoryPort) {
        return new FindPriceByParamUseCase(priceRepositoryPort);
    }
}
