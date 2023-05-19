package by.cryptodemo.services.impl;

import by.cryptodemo.advices.responses.BaseResponse;
import by.cryptodemo.dto.UserDto;
import by.cryptodemo.dto.converters.UserConverter;
import by.cryptodemo.exceptions.CustomException;
import by.cryptodemo.model.User;
import by.cryptodemo.repositories.UserRepository;
import by.cryptodemo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserConverter userConverter;

  @Override
  public ResponseEntity<?> createNewUser(UserDto userDto) {
    if (!userRepository.existsByName(userDto.getUserName())) {
      User user = userConverter.fromDto(userDto);
      userRepository.save(user);
      return ResponseEntity.ok(new BaseResponse("User successfuly created"));
    } else {
      throw new CustomException("User already existing");
    }
  }
}
