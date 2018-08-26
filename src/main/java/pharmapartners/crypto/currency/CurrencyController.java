package pharmapartners.crypto.currency;

import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;

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

/**
 * Controller class which handles the incoming requests.
 */
@RestController
public class CurrencyController {
    @Autowired
    private CurrencyRepository currencyRepository;
    private Logger logger = Logger.getLogger(CurrencyController.class.getName());

    /**
     * Creates a new currency with given request data.
     * 
     * @param body The body containing the request data.
     * 
     * @return The response entity including the location of the newly created
     *         currency.
     */
    @PostMapping("/currencies")
    public ResponseEntity<Object> createCurrency(@RequestBody Currency body) {
        logger.info("Post request \'/currencies\', create currency method:");
        logger.info("Build new currency instance.");
        Currency currency = new Currency.CurrencyBuilder().setTicker(body.getTicker()).setName(body.getName())
                .setNumberOfCoins(body.getNumberOfCoins()).setMarketCap(body.getMarketCap()).build();

        logger.info("Insert new currency instance to database.");
        currency = currencyRepository.save(currency);
        logger.info("New currency instance inserted to database.");

        logger.info("Build URI for location.");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ticker}")
                .buildAndExpand(currency.getTicker()).toUri();

        logger.info("Return reponse body.");
        return ResponseEntity.created(location).build();
    }

    /**
     * Gets the currencies with given page information.
     * 
     * @param page A pageable instance containing page information.
     * 
     * @return Page instance containing list of currencies adhere to the page
     *         information.
     */
    @GetMapping("/currencies")
    public Page<Currency> getCurrencies(Pageable page) {
        logger.info("Get request \'/currencies\', get currencies method:");
        logger.info("Return all currencies.");
        return currencyRepository.findAll(page);
    }

    /**
     * Gets the currency when it is found by the given ticker. When no currency
     * could be found an exception will be thrown.
     * 
     * @param ticker The ticker of the currency to fetch.
     * 
     * @return The currency belonging to the given ticker.
     * 
     * @throws CurrencyNotFoundException When a currency could not be found.
     */
    @GetMapping("/currencies/{ticker}")
    public Currency getCurrency(@PathVariable String ticker) throws CurrencyNotFoundException {
        logger.info("Get request \'/currencies/" + ticker + "\', get currency method:");
        logger.info("Get currency by given ticker: " + ticker + ".");
        Optional<Currency> currency = currencyRepository.findById(ticker);

        if (!currency.isPresent()) {
            logger.info("Currency with ticker: " + ticker + " not found, throw exception.");
            throw new CurrencyNotFoundException("Currency with ticker " + ticker + "was not found");
        }

        logger.info("Currency with ticker: " + ticker + " found, return currency.");
        return currency.get();
    }

    /**
     * Updates the currency by given ticker with the body information and return a
     * 'No content' response. When a currency is not found a 'Not found' response
     * will be given.
     * 
     * @param body   The body containing the request data.
     * @param ticker The ticker of the currency to update.
     *
     * @return The response entity.
     */
    @PutMapping("/currencies/{ticker}")
    public ResponseEntity<Object> updateCurrency(@RequestBody Currency body, @PathVariable String ticker) {
        logger.info("Put request \'/currencies/" + ticker + "\', update currency method:");
        logger.info("Get currency by given ticker: " + ticker + ".");
        Optional<Currency> optionalCurrency = currencyRepository.findById(ticker);

        if (!optionalCurrency.isPresent()) {
            logger.info("Currency with ticker: " + ticker + " not found.");
            return ResponseEntity.notFound().build();
        }

        logger.info("Build currency instance with ticker: " + ticker + ".");
        Currency currency = new Currency.CurrencyBuilder().setTicker(ticker).setName(body.getName())
                .setNumberOfCoins(body.getNumberOfCoins()).setMarketCap(body.getMarketCap()).build();

        logger.info("Update currency instance with ticker: " + ticker + " to database.");
        currencyRepository.save(currency);
        logger.info("Currency instance with ticker: " + ticker + " updated to database.");

        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes the currency when it is found by the given ticker. When no currency
     * could be found an exception will be thrown.
     * 
     * @param ticker The ticker of the currency to delete.
     * 
     * @throws CurrencyNotFoundException When a currency could not be found.
     */
    @DeleteMapping("/currencies/{ticker}")
    public void deleteCurrency(@PathVariable String ticker) throws CurrencyNotFoundException {
        logger.info("Delete request \'/currencies/" + ticker + "\', delete currency method:");
        logger.info("Get currency by given ticker: " + ticker + ".");
        Optional<Currency> currency = currencyRepository.findById(ticker);

        if (!currency.isPresent()) {
            logger.info("Currency with ticker: " + ticker + " not found, throw exception.");
            throw new CurrencyNotFoundException("Currency with ticker " + ticker + " was not found");
        }

        logger.info("Delete currency with ticker: " + ticker + ".");
        currencyRepository.deleteById(ticker);
        logger.info("Currency with ticker: " + ticker + " deleted.");
    }
}
