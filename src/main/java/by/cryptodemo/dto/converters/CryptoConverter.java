package by.cryptodemo.dto.converters;

import by.cryptodemo.dto.CryptoDto;
import by.cryptodemo.model.Crypto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CryptoConverter {

  public Crypto fromDto(CryptoDto cryptoDto) {
    return Crypto.builder()
        .id(cryptoDto.getId())
        .name(cryptoDto.getName())
        .nameId(cryptoDto.getNameid())
        .symbol(cryptoDto.getSymbol())
        .rank(cryptoDto.getRank())
        .priceBtc(cryptoDto.getPrice_btc())
        .priceUsd(cryptoDto.getPrice_usd())
        .percentChange1h(cryptoDto.getPercent_change_1h())
        .percentChange24h(cryptoDto.getPercent_change_24h())
        .percentChange7d(cryptoDto.getPercent_change_7d())
        .marketCapUsd(cryptoDto.getMarket_cap_usd())
        .volume24(cryptoDto.getVolume24())
        .volume24Native(cryptoDto.getVolume24_native())
        .csupply(cryptoDto.getCsupply())
        .tsupply(cryptoDto.getTsupply())
        .msupply(cryptoDto.getMsupply())
        .build();
  }

  public CryptoDto toDto(Crypto crypto) {
    return CryptoDto.builder()
        .id(crypto.getId())
        .name(crypto.getName())
        .nameid(crypto.getNameId())
        .symbol(crypto.getSymbol())
        .rank(crypto.getRank())
        .price_btc(crypto.getPriceBtc())
        .price_usd(crypto.getPriceUsd())
        .percent_change_1h(crypto.getPercentChange1h())
        .percent_change_24h(crypto.getPercentChange24h())
        .percent_change_7d(crypto.getPercentChange7d())
        .market_cap_usd(crypto.getMarketCapUsd())
        .volume24(crypto.getVolume24())
        .volume24_native(crypto.getVolume24Native())
        .csupply(crypto.getCsupply())
        .tsupply(crypto.getTsupply())
        .msupply(crypto.getMsupply())
        .build();
  }
}
