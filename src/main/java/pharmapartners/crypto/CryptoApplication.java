package pharmapartners.crypto;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pharmapartners.crypto.currency.CurrencyController;

@SpringBootApplication
public class CryptoApplication {
	private static Logger logger = Logger.getLogger(CurrencyController.class.getName());
	
	public static void main(String[] args) {
		logger.info("Start currency application.");
		SpringApplication.run(CryptoApplication.class, args);
	}
}
