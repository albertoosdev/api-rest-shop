package com.albertoosdev.shop.infrastructure.h2.repository;

import com.albertoosdev.shop.domain.model.Price;
import com.albertoosdev.shop.domain.port.PriceRepositoryPort;
import com.albertoosdev.shop.infrastructure.h2.mapper.PriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.List;

/**
 *  The price repository.
 */
@Slf4j
public class PriceRepository implements PriceRepositoryPort {

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
    public List<Price> findByApplicationDateProductIdAndBrandId(final LocalDateTime applicationDate,
                                                                final Long productId,
                                                                final Long brandId) {
        LOG.debug("::findByApplicationDateProductIdAndBrandId:: Entering findByApplicationDateProductIdAndBrandId "
                        + "with params: applicationDate: {}, productId: {}}, brandId: {}}",
                applicationDate, productId, brandId);

        return priceMapper.mapToPriceList(
                jpaRepository.findByApplicationDateProductIdAndBrandId(applicationDate, productId, brandId)
        );
    }
}
