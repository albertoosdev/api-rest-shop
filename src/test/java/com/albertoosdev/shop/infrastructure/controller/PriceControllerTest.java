package com.albertoosdev.shop.infrastructure.controller;

import com.albertoosdev.shop.domain.model.Price;
import com.albertoosdev.shop.infrastructure.api.controller.PriceRestController;
import com.albertoosdev.shop.infrastructure.api.mapper.PriceResponseMapperImpl;
import com.albertoosdev.shop.usecase.FindPriceByParamUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private FindPriceByParamUseCase findPriceByParamUseCase;

    @Spy
    private PriceResponseMapperImpl priceMapper;

    @InjectMocks
    private PriceRestController priceRestController;

    @Test
    void givenData_whenGetPriceByParam_thenReturns200OKAsResult() {
        final var mockPrice = Price.builder()
                .endDate(LocalDateTime.now())
                .startDate(LocalDateTime.now())
                .price(50.50)
                .build();

        when(findPriceByParamUseCase.execute(any(LocalDateTime.class), any(Long.class), any(Long.class)))
                .thenReturn(mockPrice);

        final var result = priceRestController.getPriceByParam(
                OffsetDateTime.now(), 1L, 1L
        );

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(mockPrice.getPrice(), result.getBody().getPrice());
        assertEquals(mockPrice.getEndDate(), result.getBody().getEndDate().toLocalDateTime());
    }

}
