package com.albertoosdev.shop.infrastructure.h2.repository;

import com.albertoosdev.shop.domain.model.Price;
import com.albertoosdev.shop.domain.model.exception.PriceNotFoundException;
import com.albertoosdev.shop.domain.port.PriceRepositoryPort;
import com.albertoosdev.shop.infrastructure.h2.entity.PriceEntity;
import com.albertoosdev.shop.infrastructure.h2.mapper.PriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 *  The price repository.
 */
@Slf4j
public class PriceRepository implements PriceRepositoryPort {

    private static final String STR_PRICE_NOT_FOUND_ERROR = "The price with " +
            "applicationDate: %s, productIs: %s and brandId: %s is not found";

    @Autowired
    private final PriceJpaRepository jpaRepository;

    private final PriceMapper priceMapper;

    private static final Logger LOG = LoggerFactory.getLogger(PriceRepository.class);

    /**
     * The constructor.
     *
     * @param jpaRepository The JPA repository
     * @param priceMapper The mapper
     */
    public PriceRepository(final PriceJpaRepository jpaRepository, final PriceMapper priceMapper) {
        this.jpaRepository = jpaRepository;
        this.priceMapper = priceMapper;
    }


    /**
     *  Find the price to apply with the parameters
     *
     * @param applicationDate   applicationDate
     * @param productId         productId
     * @param brandId           brandId
     * @return  The price to be applied
     */
    @Override
    @Cacheable("prices")
    public Price findByApplicationDateProductIdAndBrandId(final LocalDateTime applicationDate,
                                                          final Long productId,
                                                          final Long brandId) {
        LOG.debug("::findByApplicationDateProductIdAndBrandId:: Entering findByApplicationDateProductIdAndBrandId "
                        + "with params: applicationDate: {}, productId: {}}, brandId: {}}",
                applicationDate, productId, brandId);

        final var priceList = jpaRepository.findByApplicationDateProductIdAndBrandId(
                applicationDate, productId, brandId);

        if (ObjectUtils.isEmpty(priceList)) {
            final var error = String.format(STR_PRICE_NOT_FOUND_ERROR, applicationDate, productId, brandId);
            LOG.error(error);
            throw new PriceNotFoundException(error);
        }

        final var priceFounded = priceList.stream().max(Comparator.comparing(PriceEntity::getPriority))
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format(STR_PRICE_NOT_FOUND_ERROR, applicationDate, productId, brandId))
                );

        return priceMapper.mapToPrice(priceFounded);
    }
}
