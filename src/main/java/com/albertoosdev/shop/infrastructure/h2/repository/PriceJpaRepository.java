package com.albertoosdev.shop.infrastructure.h2.repository;

import com.albertoosdev.shop.infrastructure.h2.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The PriceJpaRepository interface for accessing price data from the database.
 */
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long>{

    /**
     * Finds a list of PriceEntity objects based on the application date, product ID, and brand ID.
     *
     * @param applicationDate the date to check the price validity period
     * @param productId the ID of the product
     * @param brandId the ID of the brand
     * @return a list of PriceEntity objects that match the criteria
     */
    @Query(value = "SELECT p FROM PriceEntity p " +
            "WHERE (p.startDate <= :applicationDate AND p.endDate >= :applicationDate) " +
            "AND p.productId = :productId " +
            "AND p.brandId = :brandId")
    List<PriceEntity> findByApplicationDateProductIdAndBrandId(
            @Param("applicationDate") final LocalDateTime applicationDate,
            @Param("productId") final Long productId,
            @Param("brandId") final Long brandId);

}
