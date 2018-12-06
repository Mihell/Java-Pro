import javax.persistence.*;

@Entity
@Table(name = "Transactions")
@NamedQuery(name = "findRate", query = "select r from ExchangeRate r where r.currency = :currency")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Account receiver;

    private int money;

    public Transaction() {
    }

    public Transaction(Account sender, Account receiver, int money) {
        if(sender.getMoney() < money){
            System.out.println("Not enough money!");
            throw new NotEnoughMoneyException();
        }
        this.sender = sender;
        this.receiver = receiver;
        this.money = money;
    }

    public void startTransaction(EntityManager em){
        sender.setMoney(sender.getMoney() - money);
        Query query = em.createNamedQuery("findRate", ExchangeRate.class);

        try {
            query.setParameter("currency", sender.getCurrency());
            ExchangeRate er = (ExchangeRate)query.getSingleResult();
            int senderRate = er.getRate();

            query.setParameter("currency", receiver.getCurrency());
            er = (ExchangeRate)query.getSingleResult();
            int receiverRate = er.getRate();

            receiver.setMoney(receiver.getMoney() + money * senderRate / receiverRate);
        } catch (NonUniqueResultException e) {
            System.out.println("Non unique result!");
        } catch (NoResultException e){
            System.out.println("No result!");
        }
    }
}
