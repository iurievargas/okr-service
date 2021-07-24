package com.ilegra.okr.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorMessageModel {

  private String message;
  private HttpStatus code;

}
