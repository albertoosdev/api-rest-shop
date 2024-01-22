package com.albertoosdev.shop.infrastructure.h2.mapper;

import com.albertoosdev.shop.domain.model.Price;
import com.albertoosdev.shop.infrastructure.h2.entity.PriceEntity;
import org.mapstruct.Mapper;

/**
 * The price mapper.
 */
@Mapper(componentModel = "spring")
public interface PriceMapper {

    /**
     * Map PriceEntity to Price domain object.
     *
     * @param priceEntity the entity
     * @return the Price domain object
     */
    Price mapToPrice(final PriceEntity priceEntity);

}
