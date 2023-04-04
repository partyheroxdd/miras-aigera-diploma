package kz.iitu.miras_aigera_diploma.exception_handler;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import kz.iitu.miras_aigera_diploma.exceptions.DiplomaCoreException;
import kz.iitu.miras_aigera_diploma.exceptions.security.CustomSecurityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<String>> handleValidationErrors(MethodArgumentNotValidException ex) {
    List<String> errors =
        ex.getBindingResult().getFieldErrors().stream()
            .map(i -> i.getField() + " " + i.getDefaultMessage())
            .collect(Collectors.toList());
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponseDto> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex) {
    ErrorResponseDto body = new ErrorResponseDto(ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  @ExceptionHandler({
      HttpMessageNotReadableException.class,
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
    private String message;

    ErrorResponseDto(Exception e) {
      message = e.getMessage();
      type = e.getClass().getSimpleName();
    }

    ErrorResponseDto(DiplomaCoreException e) {
      message = e.getMessage();
      type = e.getClass().getSimpleName();
    }
  }
}
