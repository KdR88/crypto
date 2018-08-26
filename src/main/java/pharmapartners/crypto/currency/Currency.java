package pharmapartners.crypto.currency;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class currency representing a currency record from the currencies table.
 */
@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    private String ticker;
    private String name;
    private Long numberOfCoins;
    private Long marketCap;

    /**
     * Default constructor needed for the JPA repository
     */
    public Currency() {}

    /**
     * Constructor used by the currency builder instance.
     * 
     * @param builder The currency builder which creates the currency entity.
     */
    private Currency(CurrencyBuilder builder) {
        this.ticker = builder.ticker;
        this.name = builder.name;
        this.numberOfCoins = builder.numberOfCoins;
        this.marketCap = builder.marketCap;
    }

    /**
     * Returns the ticker value of the currency.
     * 
     * @return A string representing the ticker.
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Returns the name of the currency.
     * 
     * @return A string representing the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of coins of a currency.
     * 
     * @return A long value representing the number of coins left.
     */
    public Long getNumberOfCoins() {
        return numberOfCoins;
    }

    /**
     * Returns the market cap of the currency.
     * 
     * @return A long value representing the market cap.
     */
    public Long getMarketCap() {
        return marketCap;
    }

    /**
     * Builder class to build up the currency, eventually the build method should be
     * called to create the currency instance.
     */
    public static class CurrencyBuilder {
        private String ticker;
        private String name;
        private Long numberOfCoins;
        private Long marketCap;

        /**
         * Sets the ticker of the currency to build.
         * 
         * @param ticker String representing the ticker.
         * 
         * @return The currency builder instance.
         */
        public CurrencyBuilder setTicker(String ticker) {
            this.ticker = ticker;
            return this;
        }

        /**
         * Sets the name of the currency to build.
         * 
         * @param name String representing the name.
         * 
         * @return The currency builder instance.
         */
        public CurrencyBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the number of coins of the currency to build.
         * 
         * @param numberOfCoins Long value representing the number of coins left.
         * 
         * @return The currency builder instance.
         */
        public CurrencyBuilder setNumberOfCoins(Long numberOfCoins) {
            this.numberOfCoins = numberOfCoins;
            return this;
        }

        /**
         * Sets the market cap of the currency to build.
         * 
         * @param marketCap Long value representing the market cap.
         * 
         * @return The currency builder instance.
         */
        public CurrencyBuilder setMarketCap(Long marketCap) {
            this.marketCap = marketCap;
            return this;
        }

        /**
         * Constructs the currency by the given builder instance.
         * 
         * @return The currency instance containing the builder variables.
         */
        public Currency build() {
            return new Currency(this);
        }
    }
}
