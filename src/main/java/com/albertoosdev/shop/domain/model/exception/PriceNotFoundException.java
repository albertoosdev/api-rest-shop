package com.albertoosdev.shop.domain.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Exception thrown when a price is not found.
 */
@AllArgsConstructor
@Getter
public class PriceNotFoundException extends RuntimeException  {

    /**
     * The error message describing the exception.
     */
    private String errorMessage;

}
