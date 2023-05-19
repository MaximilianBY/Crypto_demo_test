package by.cryptodemo.advices.responses;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BindingErrorResponse extends BaseResponse {

  private final String fieldName;
}
