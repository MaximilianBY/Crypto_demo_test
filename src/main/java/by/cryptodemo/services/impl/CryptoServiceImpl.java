package by.cryptodemo.services.impl;

import by.cryptodemo.advices.responses.BaseResponse;
import by.cryptodemo.dto.CryptoDto;
import by.cryptodemo.dto.converters.CryptoConverter;
import by.cryptodemo.exceptions.CustomException;
import by.cryptodemo.model.Crypto;
import by.cryptodemo.repositories.CryptoRepository;
import by.cryptodemo.repositories.CryptoSearchSpecification;
import by.cryptodemo.services.ICryptoService;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@EnableScheduling
public class CryptoServiceImpl implements ICryptoService {

  @Autowired
  private CryptoConverter cryptoConverter;
  @Autowired
  private CryptoRepository cryptoRepository;
  @Autowired
  private RestTemplate restTemplate;


  @Override
  public ResponseEntity<?> getCryptoPrice(String crypto) {
    CryptoSearchSpecification searchSpecification = new CryptoSearchSpecification(crypto);
    CryptoDto cryptoDto = cryptoConverter.toDto(
        Optional.ofNullable(cryptoRepository.findOne(searchSpecification)).get()
            .orElseThrow(() -> new NoResultException("Coin doesn't exist in DB")));
    return ResponseEntity.ok(
        new BaseResponse(cryptoDto.getName() + " price: " + cryptoDto.getPrice_usd()));
  }

  @Scheduled(fixedRate = 60000)
  private void updateCryptoDB() {
    String url = "https://api.coinlore.net/api/ticker/?id=90,80,48543";
    ResponseEntity<List<CryptoDto>> responseEntity = Optional.ofNullable(restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<CryptoDto>>() {
            }
        ))
        .orElseThrow(() -> new CustomException("Response is empty check API link"));
    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
      List<Crypto> cryptosList = Optional.ofNullable(responseEntity.getBody())
          .map(list -> list.stream()
              .map(cryptoConverter::fromDto)
              .toList())
          .orElseThrow(() -> new CustomException("List is empty check response from API"));
      cryptoRepository.saveAll(cryptosList);
    } else {
      throw new CustomException(
          "Failed to get coins list." + " " + responseEntity.getStatusCode() + " "
              + responseEntity.getBody());
    }
  }
}