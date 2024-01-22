package com.albertoosdev.shop.infrastructure.api.mapper;

import com.albertoosdev.shop.domain.model.Price;
import com.albertoosdev.shop.openapi.model.PriceResponseDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * The price mapper.
 */
@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    /**
     * Map Price domain object to the price response dto.
     *
     * @param price the Price domain object
     * @return the price response dto
     */
    @Mapping(source = "price.startDate", target = "startDate", qualifiedByName = "mapToOffsetDateTime")
    @Mapping(source = "price.endDate", target = "endDate", qualifiedByName = "mapToOffsetDateTime")
    PriceResponseDTO mapToPriceResponseDTO(final Price price);


    /**
     * Map LocalDateTime to OffsetDateTime.
     *
     * @param dateTime the localDateTime
     * @return the OffsetDateTime
     */
    @Named("mapToOffsetDateTime")
    default OffsetDateTime mapToOffsetDateTime(final LocalDateTime dateTime) {
        if (!ObjectUtils.isEmpty(dateTime)) {
            return dateTime.atOffset(ZoneOffset.UTC);
        }

        return null;
    }
}
