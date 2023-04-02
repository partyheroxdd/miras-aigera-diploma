package kz.iitu.miras_aigera_diploma.exceptions;

import java.sql.Timestamp;
import java.util.UUID;
import kz.iitu.miras_aigera_diploma.exceptions.security.CustomSecurityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

  @ExceptionHandler(CustomSecurityException.class)
  protected ResponseEntity<ErrorResponseDto> handleHttpError(CustomSecurityException e) {
    ErrorResponseDto body = new ErrorResponseDto(e);
    return ResponseEntity.status(e.getHttpStatus())
        .body(body);
  }

  @ExceptionHandler(DiplomaCoreException.class)
  protected ResponseEntity<ErrorResponseDto> handleHttpError(DiplomaCoreException e) {
    ErrorResponseDto body = new ErrorResponseDto(e);
    return ResponseEntity.status(e.getStatus())
        .body(body);
  }

  @ExceptionHandler({
      HttpMessageNotReadableException.class,
      MethodArgumentNotValidException.class,
      HttpRequestMethodNotSupportedException.class,
      MissingServletRequestParameterException.class
  })
  public ResponseEntity<Object> handleBadRequest(Exception e) {
    ErrorResponseDto body = new ErrorResponseDto(e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(MediaType.APPLICATION_JSON)
        .body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleInternalError(Exception e) {
    ErrorResponseDto body = new ErrorResponseDto(e);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_JSON)
        .body(body);
  }

  @Getter
  @AllArgsConstructor
  public static class ErrorResponseDto {

    private final String id = UUID.randomUUID().toString().substring(0, 8);
    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private String type;
    private String code;
    private String message;

    ErrorResponseDto(Exception e) {
      message = e.getMessage();
      type = e.getClass().getSimpleName();
    }

    ErrorResponseDto(DiplomaCoreException e) {
      message = e.getMessage();
      code = e.getCode();
      type = e.getClass().getSimpleName();
    }
  }
}
