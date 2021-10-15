package com.ilegra.okr.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequestModel {

  private String username;
  private String password;

}
