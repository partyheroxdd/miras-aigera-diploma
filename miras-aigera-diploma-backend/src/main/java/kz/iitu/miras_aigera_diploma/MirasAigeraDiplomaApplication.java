package kz.iitu.miras_aigera_diploma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MirasAigeraDiplomaApplication {

  public static void main(String[] args) {
    SpringApplication.run(MirasAigeraDiplomaApplication.class, args);
  }

}
