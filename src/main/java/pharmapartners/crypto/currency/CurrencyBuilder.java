package pharmapartners.crypto.currency;

public class CurrencyBuilder {
	private String ticker;
    private String name;
    private Long numberOfCoins;
    private Long marketCap;

    public CurrencyBuilder setTicker(final String ticker) {
        this.ticker = ticker;
        return this;
    }

    public CurrencyBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    public CurrencyBuilder setNumberOfCoins(final Long numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
        return this;
    }

    public CurrencyBuilder setMarketCap(final Long marketCap) {
        this.marketCap = marketCap;
        return this;
    }

    public Currency build() {
    	Currency currency = new Currency();
    	currency.setTicker(this.ticker);
    	currency.setName(this.name);
    	currency.setNumberOfCoins(this.numberOfCoins);
    	currency.setMarketCap(this.marketCap);

        return currency;
    }
}
