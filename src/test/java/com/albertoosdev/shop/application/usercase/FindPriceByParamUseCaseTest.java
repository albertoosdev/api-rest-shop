package com.albertoosdev.shop.application.usercase;

import com.albertoosdev.shop.domain.model.Price;
import com.albertoosdev.shop.domain.model.exception.PriceNotFoundException;
import com.albertoosdev.shop.domain.port.PriceRepositoryPort;
import com.albertoosdev.shop.application.usecase.FindPriceByParamUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPriceByParamUseCaseTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private FindPriceByParamUseCase findPriceByParamUseCase;

    @Test
    void givenParams_whenFindByApplicationDateProductIdAndBrandId_thenReturnsPriceExpected() {
        final var mockPrice = Price.builder()
                .priceList(1)
                .price(50.50)
                .priority(1)
                .build();

        when(priceRepositoryPort.findByApplicationDateProductIdAndBrandId(
                any(LocalDateTime.class),
                any(Long.class),
                any(Long.class)
        )).thenReturn(List.of(mockPrice));

        final var priceResult = findPriceByParamUseCase.execute(LocalDateTime.now(), 1L, 1L);

        assertNotNull(priceResult);
        assertEquals(priceResult, mockPrice);
    }

    @Test
    void givenParams_whenFindByApplicationDateProductIdAndBrandIdAndGetSize2_thenReturnExpectedPriceWithPriority() {
        final var mockPrice = Price.builder()
                .priceList(1)
                .price(20.50)
                .priority(0)
                .build();
        final var mockPricePriority = Price.builder()
                .priceList(2)
                .price(50.50)
                .priority(1)
                .build();
        final var priceList = List.of(mockPrice, mockPricePriority);

        when(priceRepositoryPort.findByApplicationDateProductIdAndBrandId(
                any(LocalDateTime.class),
                any(Long.class),
                any(Long.class)
        )).thenReturn(priceList);

        final var priceListResult = findPriceByParamUseCase.execute(
                LocalDateTime.now(),
                1L,
                1L
        );

        assertNotNull(priceListResult);
        Assertions.assertNotEquals(mockPrice.getPrice(), priceListResult.getPrice());
        Assertions.assertNotEquals(mockPrice.getPriceList(), priceListResult.getPriceList());
        Assertions.assertEquals(mockPricePriority.getPrice(), priceListResult.getPrice());
        Assertions.assertEquals(mockPricePriority.getPriceList(), priceListResult.getPriceList());
    }

    @Test
    void givenData_whenFindByApplicationDateProductIdAndBrandIdAndPriceNotFound_thenReturnThrowsNotFoundException() {
        when(priceRepositoryPort.findByApplicationDateProductIdAndBrandId(
                any(LocalDateTime.class),
                any(Long.class),
                any(Long.class)
        )).thenReturn(List.of());

        final var exception = assertThrows(PriceNotFoundException.class,
                () -> findPriceByParamUseCase.execute(LocalDateTime.now(), 1L, 1L)
        );

        assertNotNull(exception);
    }

}
