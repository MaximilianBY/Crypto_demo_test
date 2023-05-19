package by.cryptodemo;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "by")
public class CryptoDemoTestApplication {

  private final Environment environment;

  public CryptoDemoTestApplication(Environment environment) {
    this.environment = environment;
  }

  public static void main(String[] args) {
    SpringApplication.run(CryptoDemoTestApplication.class, args);
  }

  @Bean(name = "dataSource")
  public DataSource getDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(environment.getProperty("spring.datasource.url"));
    dataSource.setUsername(environment.getProperty("spring.datasource.username"));
    dataSource.setPassword(environment.getProperty("spring.datasource.password"));
    return dataSource;
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
