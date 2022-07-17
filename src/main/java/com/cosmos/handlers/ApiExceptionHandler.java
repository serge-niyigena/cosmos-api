package com.cosmos.handlers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.exceptions.NotFoundException;
import com.cosmos.exceptions.ThirdPartyClientException;
import com.cosmos.responses.InvalidArgumentResponse;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * Handles exceptions due to invalid input
     * 
     * @param ex      MethodArgumentNotValidException class
     * @param headers HttpHeaders class
     * @param status  HttpStatus class
     * @return ResponseEntity object
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = "Invalid input field in request body";

        BindingResult bindingResult = ex.getBindingResult();
        Map<String, Object> errors = new HashMap<String, Object>();
        if (bindingResult.hasFieldErrors()) {
            for (FieldError err : ex.getBindingResult().getFieldErrors()) {
                String key = err.getField();
                String msg = err.getDefaultMessage();
                errors.put(key, msg);
            }
        }

        if (bindingResult.hasGlobalErrors()) {
            errors.put("generalError", bindingResult.getGlobalError().getDefaultMessage());
        }

        InvalidArgumentResponse exceptionResponseBody = new InvalidArgumentResponse(
                new Date().toString(), message, errors, status.value());

        logger.error(String.format("(%s) -- [MSG] %s || [ERRORS] %s", status.value(), message, errors));

        return new ResponseEntity<Object>(exceptionResponseBody, headers, status);
    }

    /**
     * Handles invalid input exceptions
     * 
     * @param ex      InvalidInputException obj
     * @param request Webrequest instance
     * @return ResponseEntity obj
     */
    @ExceptionHandler({ InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Invalid value provided";
        Map<String, Object> errors = new HashMap<String, Object>();
        errors.put(ex.getInvalidField(), ex.getException_msg());

        InvalidArgumentResponse invalidArgumentResp = new InvalidArgumentResponse(
                new Date().toString(), message, errors, status.value());

        logger.error("{}: [MSG] {} -> {}", status.value(), message, errors);

        return new ResponseEntity<>(invalidArgumentResp, new HttpHeaders(), status);
    }

    /**
     * Handles not found exceptions
     * 
     * @param ex      NotFoundException obj
     * @param request Webrequest instance
     * @return ResponseEntity obj
     */
    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<Object> handleNotFoundExceptions(NotFoundException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Invalid value provided";
        Map<String, Object> errors = new HashMap<String, Object>();
        errors.put(ex.getInvalidField(), ex.getException_msg());

        InvalidArgumentResponse invalidArgumentResp = new InvalidArgumentResponse(
                new Date().toString(), message, errors, status.value());

        logger.error("{}: [MSG] {} -> {}", status.value(), message, errors);

        return new ResponseEntity<>(invalidArgumentResp, new HttpHeaders(), status);
    }

    /**
     * Handles 3rd party api error responses
     * 
     * @param ex
     * @param request
     */
    @ExceptionHandler(ThirdPartyClientException.class)
    public void handleThirdPartyClientException(ThirdPartyClientException ex, WebRequest request) {
        logger.error("\n[STATUS] {}\n[MSG] \n[CONTENT] {}", ex.getStatusCode().value(), ex.getMessage(),
                ex.getContent());
    }
}
