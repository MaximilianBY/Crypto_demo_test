package by.cryptodemo.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(schema = "crypto_demo_test", name = "cryptos")
public class Crypto {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "name_id", nullable = false)
  private String nameId;
  @Column(name = "symbol", nullable = false)
  private String symbol;
  @Column(name = "btc_rank", nullable = false)
  private Integer rank;
  @Column(name = "price_usd", nullable = false)
  private Double priceUsd;
  @Column(name = "percent_change_24h", nullable = false)
  private Double percentChange24h;
  @Column(name = "percent_change_1h", nullable = false)
  private Double percentChange1h;
  @Column(name = "percent_change_7d", nullable = false)
  private Double percentChange7d;
  @Column(name = "market_cap_usd", nullable = false)
  private Double marketCapUsd;
  @Column(name = "volume_24", nullable = false)
  private Double volume24;
  @Column(name = "volume_24_native", nullable = false)
  private Double volume24Native;
  @Column(name = "csupply")
  private Double csupply;
  @Column(name = "price_btc", nullable = false)
  private Double priceBtc;
  @Column(name = "tsupply")
  private Double tsupply;
  @Column(name = "msupply")
  private Double msupply;

  @OneToMany(mappedBy = "crypto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<UserCrypto> trackingCryptos;
}
