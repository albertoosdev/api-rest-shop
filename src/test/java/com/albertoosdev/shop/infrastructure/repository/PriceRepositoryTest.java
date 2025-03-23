package com.albertoosdev.shop.infrastructure.repository;

import com.albertoosdev.shop.infrastructure.h2.entity.PriceEntity;
import com.albertoosdev.shop.infrastructure.h2.mapper.PriceMapperImpl;
import com.albertoosdev.shop.infrastructure.h2.repository.PriceJpaRepository;
import com.albertoosdev.shop.infrastructure.h2.repository.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryTest {

    @Mock
    private PriceJpaRepository jpaRepository;

    @Spy
    private PriceMapperImpl priceMapper;

    @InjectMocks
    private PriceRepository priceRepository;

    @Test
    void givenData_whenFindByApplicationDateProductIdAndBrandId_thenReturnExpectedPrice() {
        final var mockPriceEntity = new PriceEntity(1L, 1L, LocalDateTime.now(),
                LocalDateTime.now(), 1, 1L, 1, 20.50, "EUR");
        final var priceList = List.of(mockPriceEntity);
        when(jpaRepository.findByApplicationDateProductIdAndBrandId(
                any(LocalDateTime.class),
                any(Long.class),
                any(Long.class)
        )).thenReturn(priceList);

        final var priceListResult = priceRepository.findByApplicationDateProductIdAndBrandId(
                LocalDateTime.now(),
                1L,
                1L
        );

        assertNotNull(priceListResult);
        Assertions.assertEquals(mockPriceEntity.getPrice(), priceListResult.getFirst().getPrice());
        Assertions.assertEquals(mockPriceEntity.getPriceList(), priceListResult.getFirst().getPriceList());
    }

    @Test
    void givenData_whenFindByApplicationDateProductIdAndBrandIdAndPriceNotFound_thenReturnThrowsNotFoundException() {
        when(jpaRepository.findByApplicationDateProductIdAndBrandId(
                any(LocalDateTime.class),
                any(Long.class),
                any(Long.class)
        )).thenReturn(null);

        final var priceListResult = priceRepository.findByApplicationDateProductIdAndBrandId(
                LocalDateTime.now(),
                1L,
                1L
        );

        assertNull(priceListResult);
    }
}
