package com.albertoosdev.shop.application.usecase;

import com.albertoosdev.shop.domain.model.Price;
import com.albertoosdev.shop.domain.model.exception.PriceNotFoundException;
import com.albertoosdev.shop.domain.port.FindPriceByParamUseCasePort;
import com.albertoosdev.shop.domain.port.PriceRepositoryPort;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Comparator;


/**
 * The Find Price By Param UseCase.
 */
public class FindPriceByParamUseCase implements FindPriceByParamUseCasePort {

    private static final String STR_PRICE_NOT_FOUND_ERROR = "The price with " +
            "applicationDate: %s, productIs: %s and brandId: %s is not found";

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
     * @throws PriceNotFoundException if no price is found
     */
    public Price execute(final LocalDateTime applicationDate, final Long productId, final Long brandId) {
        LOG.debug("::execute:: Entering execute with params: applicationDate: {}, productId: {}, brandId: {}",
                applicationDate, productId, brandId);
        final var priceList = priceRepositoryPort.findByApplicationDateProductIdAndBrandId(
                applicationDate, productId, brandId);

        if (ObjectUtils.isEmpty(priceList)) {
            final var error = String.format(STR_PRICE_NOT_FOUND_ERROR, applicationDate, productId, brandId);
            LOG.error(error);
            throw new PriceNotFoundException(error);
        }

        return priceList.stream().max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format(STR_PRICE_NOT_FOUND_ERROR, applicationDate, productId, brandId))
                );

    }

}

