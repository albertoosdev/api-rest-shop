package com.albertoosdev.shop.infrastructure.spring.config;

import com.albertoosdev.shop.domain.port.FindPriceByParamUseCasePort;
import com.albertoosdev.shop.domain.port.PriceRepositoryPort;
import com.albertoosdev.shop.application.usecase.FindPriceByParamUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up use case related beans.
 */
@Configuration
public class UseCaseConfig {

    /**
     * Creates a FindPriceByParamUseCasePort bean that provides the implementation of the use case
     * for finding prices by parameters.
     *
     * @param priceRepositoryPort the repository port for accessing price data
     * @return a FindPriceByParamUseCasePort implementation
     */
    @Bean
    FindPriceByParamUseCasePort findPriceByParamUseCasePort(final PriceRepositoryPort priceRepositoryPort) {
        return new FindPriceByParamUseCase(priceRepositoryPort);
    }
}
