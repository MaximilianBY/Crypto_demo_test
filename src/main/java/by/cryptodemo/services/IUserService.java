package by.cryptodemo.services;

import by.cryptodemo.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface IUserService {
  ResponseEntity<?> createNewUser(UserDto userDto);
}
