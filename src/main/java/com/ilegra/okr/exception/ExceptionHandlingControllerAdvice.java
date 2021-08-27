package com.ilegra.okr.exception;

import com.ilegra.okr.model.response.ErrorMessageResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class ExceptionHandlingControllerAdvice {

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(IllegalArgumentException.class)
  public ErrorMessageResponseModel validateNoContent(IllegalArgumentException ex) {

    ErrorMessageResponseModel errorMessageModel = new ErrorMessageResponseModel();
    errorMessageModel.setCode(HttpStatus.NO_CONTENT.value());
    errorMessageModel.setMessage(ex.getMessage());

    return errorMessageModel;
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> validateInternalServerError() {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
