package by.cryptodemo.services;

import org.springframework.http.ResponseEntity;

public interface ICryptoService {

  ResponseEntity<?> getCryptoPrice(String crypto);
}
