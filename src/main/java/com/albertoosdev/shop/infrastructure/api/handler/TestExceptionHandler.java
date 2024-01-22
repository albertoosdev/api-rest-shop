package com.albertoosdev.shop.infrastructure.api.handler;

import com.albertoosdev.shop.domain.model.exception.PriceNotFoundException;
import com.albertoosdev.shop.openapi.model.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Test Controller Advice.
 */
@ControllerAdvice
public class TestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(PriceNotFoundException exception,
                                                                    WebRequest request) {
        final var errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorCode(HttpStatus.NOT_FOUND.name());
        errorResponseDTO.setErrorMessage(exception.getErrorMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponseDTO);
    }

}
