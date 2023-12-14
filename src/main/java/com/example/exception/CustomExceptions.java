package com.example.exception;

public class CustomExceptions {

  public static class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException(String message) {
      super(message);
    }
  }

  public static class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException(String message) {
      super(message);
    }
  }

  public static class InputInvalidException extends RuntimeException {
    public InputInvalidException(String message){
      super(message);
    }
  }
}
