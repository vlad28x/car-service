package com.aston.carservice.controller.handler;

import com.aston.carservice.exception.BadRequestException;
import com.aston.carservice.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.time.Instant;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseError handleNotFoundException(NotFoundException ex) {
        return handle(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseError handleBadRequestException(BadRequestException ex) {
        return handle(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseError handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return handle(ex, "JSON parse error");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return handle(ex, "Failed to convert path variable value to required type");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Function<FieldError, String> getMessageFromFieldError =
                (fieldError) -> (fieldError.getDefaultMessage() == null) ? "" : fieldError.getDefaultMessage();
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(FieldError.class::cast)
                .collect(Collectors.toMap(FieldError::getField, getMessageFromFieldError));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Map<Path, String> handleConstraintViolationException(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
    }

    /* @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler(value = Exception.class)
     public ResponseError handleAllException(Exception ex) {
         return handle(ex);
     }
 */
    private ResponseError handle(Exception ex) {
        logger.error("Exception Handling: " + ex);
        return new ResponseError(ex.getMessage());
    }

    private ResponseError handle(Exception ex, String message) {
        logger.error("Exception Handling: " + ex);
        return new ResponseError(message);
    }

    private static class ResponseError {

        private final Instant time = Instant.now();
        private String message;

        public ResponseError(String message) {
            this.message = message;
        }

        public Instant getTime() {
            return time;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
