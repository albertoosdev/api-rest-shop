package com.albertoosdev.shop.domain.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Price Not Found Exception
 */
@AllArgsConstructor
@Getter
public class PriceNotFoundException extends RuntimeException  {

    private String errorMessage;

}
