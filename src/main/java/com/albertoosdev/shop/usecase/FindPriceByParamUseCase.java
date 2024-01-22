package com.albertoosdev.shop.usecase;

import com.albertoosdev.shop.domain.model.Price;
import com.albertoosdev.shop.domain.port.FindPriceByParamUseCasePort;
import com.albertoosdev.shop.domain.port.PriceRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


/**
 * The Find Price By Param UseCase.
 */
public class FindPriceByParamUseCase implements FindPriceByParamUseCasePort {

    private final PriceRepositoryPort priceRepositoryPort;

    private static final Logger LOG = LoggerFactory.getLogger(FindPriceByParamUseCase.class);

    /**
     * The constructor
     *
     * @param priceRepositoryPort repository port.
     */
    public FindPriceByParamUseCase(final PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    /**
     * Find the price by applicationDate, productId and brandId.
     *
     * @param applicationDate the application date
     * @param productId       the product id
     * @param brandId         the brand id
     * @return the found price
     */

    public Price execute(final LocalDateTime applicationDate, final Long productId, final Long brandId) {
        LOG.debug("::execute:: Entering execute with params: "
                        + "applicationDate: {}, productId: {}, brandId:{}",
                applicationDate, productId, brandId);

        return priceRepositoryPort.findByApplicationDateProductIdAndBrandId(applicationDate, productId, brandId);
    }

}

