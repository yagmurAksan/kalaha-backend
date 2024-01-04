package com.bol.kalaha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PitNotFoundException.class)
    public ResponseEntity<String> handlePitNotFoundException(PitNotFoundException e) {
        return new ResponseEntity<>("Pit not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SowingNotApplicableException.class)
    public ResponseEntity<String> handleSowingNotApplicableException(SowingNotApplicableException e) {
        return new ResponseEntity<>("Sowing not applicable: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
