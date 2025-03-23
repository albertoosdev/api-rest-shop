package com.albertoosdev.shop.domain.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * The price domain object.
 */
@Data
@Builder
public class Price {


    /**
     * The unique identifier of the price.
     */
    private Long id;

    /**
     * The identifier of the brand.
     */
    private Long brandId;

    /**
     * The start date of the price validity period.
     */
    private LocalDateTime startDate;

    /**
     * The end date of the price validity period.
     */
    private LocalDateTime endDate;

    /**
     * The price list identifier.
     */
    private Integer priceList;

    /**
     * The identifier of the product.
     */
    private Long productId;

    /**
     * The priority of the price.
     */
    private Integer priority;

    /**
     * The price value.
     */
    private Double price;

    /**
     * The currency of the price.
     */
    private String curr;

}
