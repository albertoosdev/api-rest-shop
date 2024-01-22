package com.albertoosdev.shop.usercase;

import com.albertoosdev.shop.domain.model.Price;
import com.albertoosdev.shop.domain.port.PriceRepositoryPort;
import com.albertoosdev.shop.usecase.FindPriceByParamUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPriceByParamUseCaseTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private FindPriceByParamUseCase findPriceByParamUseCase;

    @Test
    void givenParams_wheFindByApplicationDateProductIdAndBrandId_thenReturnsPriceExpected() {
        final var mockPrice = Price.builder()
                .priceList(1)
                .price(50.50)
                .build();

        when(priceRepositoryPort.findByApplicationDateProductIdAndBrandId(
                any(LocalDateTime.class),
                any(Long.class),
                any(Long.class)
        )).thenReturn(mockPrice);

        final var priceResult = findPriceByParamUseCase.execute(LocalDateTime.now(), 1L, 1L);

        assertNotNull(priceResult);
        assertEquals(priceResult, mockPrice);
    }

}
