package com.albertoosdev.shop.infrastructure.h2.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up caching in the application.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Creates a CacheManager bean that manages caches with the name "prices".
     *
     * @return a CacheManager instance
     */
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("prices");
    }
}
