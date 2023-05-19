package by.cryptodemo.advices;

import by.cryptodemo.advices.responses.BaseResponse;
import by.cryptodemo.advices.responses.BindingErrorResponse;
import by.cryptodemo.exceptions.CustomException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingControllerAdvice {

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<List<BindingErrorResponse>> onConstraintValidationException(
      ConstraintViolationException e) {
    final List<BindingErrorResponse> responses = e.getConstraintViolations().stream()
        .map(
            error -> {
              BindingErrorResponse response = BindingErrorResponse.builder()
                  .fieldName(error.getPropertyPath().toString())
                  .message(error.getMessage())
                  .build();
              log.warn(e.getClass().getSimpleName() + ": " + response.getFieldName() + ": "
                  + response.getMessage());
              return response;
            }
        )
        .collect(Collectors.toList());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(responses);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<List<BindingErrorResponse>> onMethodArgumentNotValidException(
      MethodArgumentNotValidException e
  ) {
    final List<BindingErrorResponse> violations = e.getBindingResult().getFieldErrors().stream()
        .map(error -> {
              BindingErrorResponse response = BindingErrorResponse.builder()
                  .fieldName(error.getField())
                  .message(error.getDefaultMessage())
                  .build();
              log.warn(e.getClass().getSimpleName() + ": " + response.getFieldName() + ": "
                  + response.getMessage());
              return response;
            }
        )
        .collect(Collectors.toList());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(violations);
  }

  @ExceptionHandler({CustomException.class})
  public ResponseEntity<BaseResponse> onNoContent(RuntimeException e) {
    log.warn(e.getClass().getSimpleName() + ": message: " + e.getMessage());
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .body(BaseResponse.builder()
            .message(e.getMessage())
            .build());
  }

  @ExceptionHandler({NoResultException.class})
  public ResponseEntity<BaseResponse> onNotFoundException(RuntimeException e) {
    log.warn(e.getClass().getSimpleName() + ": message: " + e.getMessage());
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(BaseResponse.builder()
            .message(e.getMessage())
            .build());
  }
}
