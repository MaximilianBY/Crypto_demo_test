package by.cryptodemo.services;

import org.springframework.http.ResponseEntity;

public interface IUserCryptoService {
  ResponseEntity<?> addCryptoToUsersList(String userName, String cryptoName);
}
