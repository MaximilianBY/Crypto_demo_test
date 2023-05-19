package by.cryptodemo.controllers;

import by.cryptodemo.dto.UserDto;
import by.cryptodemo.services.ICryptoService;
import by.cryptodemo.services.IUserCryptoService;
import by.cryptodemo.services.IUserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

  @Autowired
  private ICryptoService cryptoService;
  @Autowired
  private IUserService userService;
  @Autowired
  private IUserCryptoService userCryptoService;

  @PostMapping("/sign-up")
  public ResponseEntity<?> createNewUser(@RequestBody @Valid UserDto userDto) {
    return userService.createNewUser(userDto);
  }

  @PostMapping("/get-price/{crypto}")
  public ResponseEntity<?> getCryptoPriceByNameOrNameIdOrSymbol(
      @PathVariable("crypto") String crypto) {
    return cryptoService.getCryptoPrice(crypto);
  }

  @PostMapping("/add-crypto-favourite/{username}/{crypto}")
  public ResponseEntity<?> addCryptoToUsersFavouriteList(
      @PathVariable("username") String userName,
      @PathVariable("crypto") String crypto) {
    return userCryptoService.addCryptoToUsersList(userName, crypto);
  }
}
