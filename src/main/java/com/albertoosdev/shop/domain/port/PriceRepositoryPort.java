package com.albertoosdev.shop.domain.port;

import com.albertoosdev.shop.domain.model.Price;

import java.time.LocalDateTime;

/**
 * The price repository port interface.
 */
public interface PriceRepositoryPort {


    /**
     *  Find the price to apply with the parameters
     *
     * @param applicationDate   applicationDate
     * @param productId         productId
     * @param brandId           brandId
     * @return  The price to be applied
     */
    Price findByApplicationDateProductIdAndBrandId(final LocalDateTime applicationDate,
                                                   final Long productId,
                                                   final Long brandId);
}
