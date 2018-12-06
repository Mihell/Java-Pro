import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExchangeRate {

    @Id
    private Currency currency;
    private String name;
    private int rate;

    public ExchangeRate() {
    }

    public ExchangeRate(Currency currency, int rate) {
        this.currency = currency;
        this.rate = rate;
        this.name = currency.toString();
    }

    public int getRate() {
        return rate;
    }
}
