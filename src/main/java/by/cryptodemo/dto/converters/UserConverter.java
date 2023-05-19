package by.cryptodemo.dto.converters;

import by.cryptodemo.dto.UserDto;
import by.cryptodemo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {

  public User fromDto(UserDto userDto) {
    return User.builder()
        .name(userDto.getUserName())
        .build();
  }
}
