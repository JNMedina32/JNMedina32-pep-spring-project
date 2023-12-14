package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.exception.CustomExceptions.*;

@ControllerAdvice
public class GlobalExceptionsHandler {
  

  @ExceptionHandler(InvalidUsernameOrPasswordException.class)
  public ResponseEntity<String> handleInvalidUsernameOrPassword(InvalidUsernameOrPasswordException e){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  @ExceptionHandler(UsernameAlreadyTakenException.class)
  public ResponseEntity<String> handleUsernameAlreadyTaken(UsernameAlreadyTakenException e){
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  @ExceptionHandler(InputInvalidException.class)
  public ResponseEntity<String> handleInputEmptyOrShort(InputInvalidException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

}
