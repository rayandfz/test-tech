package fr.rayandfz.back.error;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import fr.rayandfz.back.model.ProductCategory;
import fr.rayandfz.back.model.ProductInventoryStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller advice to handle exceptions globally across the whole application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles exceptions thrown by validation failures for request body fields.
     *
     * @param ex      the exception thrown when validation fails
     * @param request the current web request
     * @return a ResponseEntity containing the validation errors and the BAD_REQUEST status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(final MethodArgumentNotValidException ex, final WebRequest request) {
        final Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        final Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions that occur when the request body cannot be read properly,
     * typically due to type mismatch or invalid format in JSON payload. This method
     * specifically handles cases where enum types like {@code ProductCategory} or
     * {@code ProductInventoryStatus} cannot be deserialized from the provided string values.
     *
     * @param ex      the {@link HttpMessageNotReadableException} thrown when the request body
     *                cannot be read or deserialized properly.
     * @param request the current web request that resulted in the exception. This is not
     *                directly used in the method but is required for matching the method
     *                signature for exception handlers.
     * @return a {@link ResponseEntity<Object>} containing a detailed error message
     * with the timestamp, HTTP status code, and a message indicating the cause
     * of the error. For errors related to deserialization of {@code ProductCategory}
     * and {@code ProductInventoryStatus}, it provides a message detailing the
     * invalid value and suggesting the valid enum values. For other causes of
     * {@code HttpMessageNotReadableException}, it delegates to a generic error
     * handler method.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final WebRequest request) {
        final Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException) {
            final InvalidFormatException ife = (InvalidFormatException) cause;

            final Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("status", HttpStatus.BAD_REQUEST.value());

            if (ProductCategory.class.isAssignableFrom(ife.getTargetType())) {
                final String invalidValue = ife.getValue().toString();
                body.put("message", "Invalid category: " + invalidValue + ". Valid categories are: " + Arrays.toString(ProductCategory.values()));
            } else if (ProductInventoryStatus.class.isAssignableFrom(ife.getTargetType())) {
                final String invalidValue = ife.getValue().toString();
                body.put("message", "Invalid inventory status: " + invalidValue + ". Valid statuses are: " + Arrays.toString(ProductInventoryStatus.values()));
            } else {
                return handleAllExceptions(ex, request);
            }
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        return handleAllExceptions(ex, request);
    }

    /**
     * Handles all other exceptions that do not have specific handlers.
     *
     * @param ex      the exception that was thrown
     * @param request the current web request
     * @return a ResponseEntity containing a generic error message and the INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(final Exception ex, final WebRequest request) {
        final Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("message", "An unexpected error occurred ");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
