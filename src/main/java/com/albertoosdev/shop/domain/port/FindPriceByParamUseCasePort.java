package com.albertoosdev.shop.domain.port;


import com.albertoosdev.shop.domain.model.Price;

import java.time.LocalDateTime;

/**
 * The FindPriceByParamUseCasePort interface.
 */
public interface FindPriceByParamUseCasePort {

    /**
     *  Find the price by applicationDate, productId and brandId.
     *
     * @param applicationDate the application date
     * @param productId       the product id
     * @param brandId         the brand id
     * @return                the found price
     */
    Price execute(final LocalDateTime applicationDate, final Long productId, final Long brandId);
}
