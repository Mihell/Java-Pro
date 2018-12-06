import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private Client owner;

    private long money;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> outgoing = new ArrayList<Transaction>();

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> incoming = new ArrayList<Transaction>();

    @Transient
    private Currency currency;

    @Column(name="currency")
    private String currencyName;

    public Account() {
    }

    public Account(long money) {
        this.money = money;
    }

    public Account(long money, Currency currency) {
        this.money = money;
        this.currency = currency;
        this.currencyName = currency.toString();
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owner=" + owner +
                ", money=" + money +
                ", outgoing=" + outgoing +
                ", incoming=" + incoming +
                '}';
    }
}
