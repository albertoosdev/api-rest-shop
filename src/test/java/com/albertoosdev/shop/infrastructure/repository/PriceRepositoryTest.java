package com.albertoosdev.shop.infrastructure.repository;

import com.albertoosdev.shop.domain.model.exception.PriceNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        final var priceResult = priceRepository.findByApplicationDateProductIdAndBrandId(
                LocalDateTime.now(),
                1L,
                1L
        );

        assertNotNull(priceResult);
        Assertions.assertEquals(mockPriceEntity.getPrice(), priceResult.getPrice());
        Assertions.assertEquals(mockPriceEntity.getPriceList(), priceResult.getPriceList());
    }

    @Test
    void givenData_whenFindByApplicationDateProductIdAndBrandIdAndGetSize2_thenReturnExpectedPriceWithPriority() {
        final var mockPriceEntity = new PriceEntity(1L, 1L, LocalDateTime.now(),
                LocalDateTime.now(), 1, 1L, 0, 20.50, "EUR");
        final var mockPriceEntityPriority = new PriceEntity(2L, 1L, LocalDateTime.now(),
                LocalDateTime.now(), 2, 1L, 1, 50.50, "EUR");
        final var priceList = List.of(mockPriceEntity, mockPriceEntityPriority);

        when(jpaRepository.findByApplicationDateProductIdAndBrandId(
                any(LocalDateTime.class),
                any(Long.class),
                any(Long.class)
        )).thenReturn(priceList);

        final var priceResult = priceRepository.findByApplicationDateProductIdAndBrandId(
                LocalDateTime.now(),
                1L,
                1L
        );

        assertNotNull(priceResult);
        Assertions.assertNotEquals(mockPriceEntity.getPrice(), priceResult.getPrice());
        Assertions.assertNotEquals(mockPriceEntity.getPriceList(), priceResult.getPriceList());
        Assertions.assertEquals(mockPriceEntityPriority.getPrice(), priceResult.getPrice());
        Assertions.assertEquals(mockPriceEntityPriority.getPriceList(), priceResult.getPriceList());
    }

    @Test
    void givenData_whenFindByApplicationDateProductIdAndBrandIdAndPriceNotFoun_thenReturnThrowsNotFoundException() {
        when(jpaRepository.findByApplicationDateProductIdAndBrandId(
                any(LocalDateTime.class),
                any(Long.class),
                any(Long.class)
        )).thenReturn(null);

        final var exception = assertThrows(PriceNotFoundException.class,
                () -> priceRepository.findByApplicationDateProductIdAndBrandId(LocalDateTime.now(), 1L, 1L)
        );

        assertNotNull(exception);
    }
}
