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

    private Long productId;
    private Long brandId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;

}
