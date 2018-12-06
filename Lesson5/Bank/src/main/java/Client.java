import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<Account>();

    public Client(String name) {
        this.name = name;
    }

    public Client() {
    }

    public void addAccount(Account account){
        account.setOwner(this);
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public int getTotalMoney(EntityManager em){
        int total = 0;
        for(Account account : accounts){
            Query query = em.createQuery("select r from ExchangeRate r where r.currency = :cur");
            query.setParameter("cur", account.getCurrency());
            try {
                ExchangeRate er = (ExchangeRate)query.getSingleResult();
                total += account.getMoney() * er.getRate();
            } catch (NonUniqueResultException e) {
                System.out.println("Non unique result!");
            } catch (NoResultException e){
                System.out.println("No result!");
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
