package pharmapartners.crypto.currency;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="currencies")
public class Currency {
	@Id
    private String ticker;
    private String name;
    private Long numberOfCoins;
    private Long marketCap;
    
    public Currency() {}
 
    private Currency(CurrencyBuilder builder) {
        this.ticker = builder.ticker;
        this.name = builder.name;
        this.numberOfCoins = builder.numberOfCoins;
        this.marketCap = builder.marketCap;
    }
 
    public String getTicker() {
		return ticker;
	}

	public String getName() {
		return name;
	}

	public Long getNumberOfCoins() {
		return numberOfCoins;
	}

	public Long getMarketCap() {
		return marketCap;
	}
	
	public static class CurrencyBuilder {
		private String ticker;
		private String name;
		private Long numberOfCoins;
		private Long marketCap;

	    public CurrencyBuilder setTicker(String ticker) {
	        this.ticker = ticker;
	        return this;
	    }

	    public CurrencyBuilder setName(String name) {
	        this.name = name;
	        return this;
	    }
	    
	    public CurrencyBuilder setNumberOfCoins(Long numberOfCoins) {
	        this.numberOfCoins = numberOfCoins;
	        return this;
	    }
	    
	    public CurrencyBuilder setMarketCap(Long marketCap) {
	        this.marketCap = marketCap;
	        return this;
	    }

	    public Currency build() {
	        return new Currency(this);
	    }
	}
}
