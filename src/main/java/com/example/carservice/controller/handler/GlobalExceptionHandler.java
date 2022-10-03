package com.example.carservice.controller.handler;

import com.example.carservice.exception.BadRequestException;
import com.example.carservice.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

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
    @ExceptionHandler(value = Exception.class)
    public ResponseError handleAllException(Exception ex) {
        return handle(ex);
    }

    private ResponseError handle(Exception ex) {
        logger.error("Exception Handling: " + ex);
        return new ResponseError(ex.getMessage());
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
