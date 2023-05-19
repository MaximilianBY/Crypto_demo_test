package by.cryptodemo.services.impl;

import by.cryptodemo.advices.responses.BaseResponse;
import by.cryptodemo.exceptions.CustomException;
import by.cryptodemo.model.Crypto;
import by.cryptodemo.model.User;
import by.cryptodemo.model.UserCrypto;
import by.cryptodemo.repositories.CryptoRepository;
import by.cryptodemo.repositories.CryptoSearchSpecification;
import by.cryptodemo.repositories.UserCryptoRepository;
import by.cryptodemo.repositories.UserRepository;
import by.cryptodemo.services.IUserCryptoService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableScheduling
public class UserCryptoServiceImpl implements IUserCryptoService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CryptoRepository cryptoRepository;
  @Autowired
  private UserCryptoRepository userCryptoRepository;

  @Override
  public ResponseEntity<?> addCryptoToUsersList(String userName, String cryptoName) {
    CryptoSearchSpecification searchSpecification = new CryptoSearchSpecification(cryptoName);
    User user = userRepository.getUserByName(userName)
        .orElseThrow(() -> new NoResultException("User doesn't exist"));
    Crypto crypto = Optional.ofNullable(cryptoRepository.findOne(searchSpecification)).get()
        .orElseThrow(() -> new NoResultException("Coin doesn't exist in DB"));
    if (!userCryptoRepository.existsByUserAndCrypto(user, crypto)) {
      UserCrypto userCrypto = UserCrypto.builder()
          .user(user)
          .crypto(crypto)
          .currentPrice(crypto.getPriceUsd())
          .firstReg(LocalDateTime.now())
          .build();
      userCryptoRepository.save(userCrypto);
      return ResponseEntity.ok(new BaseResponse("Coin successfuly added to your list"));
    } else {
      throw new CustomException("This coin have already existed in your list");
    }
  }

  @Scheduled(fixedRate = 70000)
  private void checkPriceChange() {
    List<Crypto> cryptosList = cryptoRepository.findAll();

    for (Crypto code : cryptosList) {
      List<UserCrypto> userCryptoList = userCryptoRepository.getAllByCrypto(code);
      for (UserCrypto crypto : userCryptoList) {
        double percentageChange =
            Math.abs(code.getPriceUsd() - crypto.getCurrentPrice()) / crypto.getCurrentPrice()
                * 100;
        if (percentageChange > 1) {
          log.warn(
              "The price of " + crypto.getCrypto().getSymbol() + " for user " + crypto.getUser()
                  .getName()
                  + " has changed by " + String.format("%.2f",percentageChange) + "% since registration");
        }
      }
    }
  }
}
