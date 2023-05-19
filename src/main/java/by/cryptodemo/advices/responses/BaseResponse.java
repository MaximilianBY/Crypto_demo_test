package by.cryptodemo.advices.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
public class BaseResponse {

  private final String message;
}
