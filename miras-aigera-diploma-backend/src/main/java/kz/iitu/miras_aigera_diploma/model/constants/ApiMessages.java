package kz.iitu.miras_aigera_diploma.model.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiMessages {

  public static final String BAD_CREDENTIALS = "BAD_CREDENTIALS";
  public static final String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
  public static final String PHONE_NUMBER_EXISTS = "PHONE_NUMBER_EXISTS";
  public static final String INVALID_TOKEN = "Expired or Invalid Token";
  public static final String INVALID_USERNAME = "Username must be 12 digit IIN format";
  public static final String POST_NOT_FOUND = "POST_NOT_FOUND";
  public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
}
