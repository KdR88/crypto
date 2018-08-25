package pharmapartners.crypto.currency;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CurrencyController {
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@PostMapping("/currencies")
	public ResponseEntity<Object> createCurrency(@RequestBody Currency body) {
		Currency currency = new Currency
				.CurrencyBuilder()
				.setTicker(body.getTicker())
				.setName(body.getName())
				.setNumberOfCoins(body.getNumberOfCoins())
				.setMarketCap(body.getMarketCap())
				.build();
		
		currency = currencyRepository.save(currency);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ticker}")
				.buildAndExpand(currency.getTicker()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/currencies")
	public Page<Currency> getCurrencies(Pageable page) {
		return currencyRepository.findAll(page);
	}
	
	@GetMapping("/currencies/{ticker}")
	public Currency getCurrency(@PathVariable String ticker) {
		Optional<Currency> currency = currencyRepository.findById(ticker);
		return currency.get();
	}
	
	@PutMapping("/currencies/{ticker}")
	public ResponseEntity<Object> updateCurrency(@RequestBody Currency body, @PathVariable String ticker) {

		Optional<Currency> optionalCurrency = currencyRepository.findById(ticker);
		
		if (!optionalCurrency.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Currency currency = new Currency
				.CurrencyBuilder()
				.setTicker(ticker)
				.setName(body.getName())
				.setNumberOfCoins(body.getNumberOfCoins())
				.setMarketCap(body.getMarketCap())
				.build();
		
		currencyRepository.save(currency);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/currencies/{ticker}")
	public void deleteCurrency(@PathVariable String ticker) {
		currencyRepository.deleteById(ticker);
	}
}
