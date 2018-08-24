package pharmapartners.crypto.currency;

public class Currency {
    private Long id;
    private String ticker;
    private String name;
    private Long numberOfCoins;
    private Long marketCap;
    
	public Long getId() {
		return id;
	}
    
    public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNumberOfCoins() {
		return numberOfCoins;
	}

	public void setNumberOfCoins(Long numberOfCoins) {
		this.numberOfCoins = numberOfCoins;
	}

	public Long getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Long marketCap) {
		this.marketCap = marketCap;
	}
	
	public static CurrencyBuilder builder() {
        return new CurrencyBuilder();
    }
}
