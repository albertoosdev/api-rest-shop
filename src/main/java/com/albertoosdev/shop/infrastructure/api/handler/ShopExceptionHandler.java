package com.albertoosdev.shop.infrastructure.api.handler;

import com.albertoosdev.shop.domain.model.exception.PriceNotFoundException;
import com.albertoosdev.shop.openapi.model.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for the Shop application.
 * This class handles specific exceptions and returns appropriate HTTP responses.
 */
@ControllerAdvice
public class ShopExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     *
     * @param exception the exception thrown when a price is not found
     * @param request the current web request
     * @return a ResponseEntity containing an ErrorResponseDTO with the error details
     */
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

    /**
     * Handles MethodArgumentTypeMismatchException and returns a 400 Bad Request response.
     *
     * @param exception the exception thrown when a method argument type mismatch occurs
     * @param request the current web request
     * @return a ResponseEntity containing an ErrorResponseDTO with the error details
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception,
            WebRequest request) {

        final var errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorCode(HttpStatus.BAD_REQUEST.name());
        errorResponseDTO.setErrorMessage(exception.getLocalizedMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDTO);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        final var errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorCode(HttpStatus.BAD_REQUEST.name());
        errorResponseDTO.setErrorMessage(ex.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDTO);
    }

}
