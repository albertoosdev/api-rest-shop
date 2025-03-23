package com.albertoosdev.shop.infrastructure.h2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *  The price entity.
 */
@Entity
@Table(name = "PRICES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriceEntity {

    /**
     * The unique identifier of the price.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The identifier of the brand.
     */
    @Column(name = "BRAND_ID")
    private Long brandId;

    /**
     * The start date of the price validity period.
     */
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    /**
     * The end date of the price validity period.
     */
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    /**
     * The price list identifier.
     */
    @Column(name = "PRICE_LIST")
    private Integer priceList;

    /**
     * The identifier of the product.
     */
    @Column(name = "PRODUCT_ID")
    private Long productId;

    /**
     * The priority of the price.
     */
    @Column(name = "PRIORITY")
    private Integer priority;

    /**
     * The price value.
     */
    @Column(name = "PRICE")
    private Double price;

    /**
     * The currency of the price.
     */
    @Column(name = "CURR")
    private String curr;

}
