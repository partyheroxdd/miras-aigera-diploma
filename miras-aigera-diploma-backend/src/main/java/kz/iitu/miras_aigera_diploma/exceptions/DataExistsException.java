package kz.iitu.miras_aigera_diploma.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class DataExistsException extends RuntimeException {

  private String message;
  private HttpStatus httpStatus;

  public DataExistsException(String message) {
    this.message = message;
  }

  public DataExistsException(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }
}
