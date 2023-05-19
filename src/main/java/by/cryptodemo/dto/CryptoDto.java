package by.cryptodemo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoDto {

  @NotNull(message = "ID can't be null")
  private Long id;
  @NotBlank(message = "Name can't be empty or null or contain only space")
  private String name;
  @NotBlank(message = "Name id can't be empty or null or contain only space")
  private String nameid;
  @NotBlank(message = "Symbol can't be empty or null or contain only space")
  private String symbol;
  @NotNull(message = "Rank can't be null")
  private Integer rank;
  @NotNull(message = "Price usd can't be null")
  private Double price_usd;
  @NotNull(message = "Price usd can't be null")
  private Double price_btc;
  @NotNull(message = "Percent 24h can't be null")
  private Double percent_change_24h;
  @NotNull(message = "Percent 1h can't be null")
  private Double percent_change_1h;
  @NotNull(message = "Percent 7d can't be null")
  private Double percent_change_7d;
  @NotNull(message = "Market cap usd can't be null")
  private Double market_cap_usd;
  @NotNull(message = "Volume 24 can't be null")
  private Double volume24;
  @NotNull(message = "Volume 24 native can't be null")
  private Double volume24_native;
  private Double csupply;
  private Double tsupply;
  private Double msupply;
}
