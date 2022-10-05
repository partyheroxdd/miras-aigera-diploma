package kz.iitu.miras_aigera_diploma.exceptions.security;


import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class CustomSecurityException extends RuntimeException {

  private String message;
  private HttpStatus httpStatus;

  public CustomSecurityException(String message) {
    this.message = message;
  }

  public CustomSecurityException(String message, HttpStatus httpStatus) {
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
