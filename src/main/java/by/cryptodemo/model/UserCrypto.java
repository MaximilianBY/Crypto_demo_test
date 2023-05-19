package by.cryptodemo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Entity
@Table(schema = "crypto_demo_test", name = "user_cryptos")
public class UserCrypto extends BaseEntity {

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
  private User user;
  @ManyToOne(optional = false)
  @JoinColumn(name = "crypto_id", nullable = false, referencedColumnName = "id")
  private Crypto crypto;

  @Column(name = "current_price", nullable = false)
  private Double currentPrice;

  @Column(name = "first_time_reg", nullable = false)
  private LocalDateTime firstReg;

  @Column(name = "price_update_date")
  private LocalDateTime priceUpdDate;
}
