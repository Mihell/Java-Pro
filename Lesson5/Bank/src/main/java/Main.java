import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bank");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        ExchangeRate uah = new ExchangeRate(Currency.UAH, 1);
        ExchangeRate eur = new ExchangeRate(Currency.EUR, 32);
        ExchangeRate usd = new ExchangeRate(Currency.USD, 28);

        Account account = new Account(10000, Currency.EUR);
        Client misha = new Client("Misha");
        misha.addAccount(account);
        Client alla = new Client("Alla");
        Account account1 = new Account(2000, Currency.UAH);
        alla.addAccount(account1);

        Transaction transaction = new Transaction(account, account1, 1000);

        em.getTransaction().begin();
            em.persist(uah);
            em.persist(eur);
            em.persist(usd);
            em.persist(transaction);
            em.persist(misha);
            em.persist(alla);
        em.getTransaction().commit();

        transaction.startTransaction(em);
        Transaction transaction2 = new Transaction(account, account1, 2000);

        em.getTransaction().begin();
            em.persist(transaction2);
        em.getTransaction().commit();
        transaction2.startTransaction(em);

        em.getTransaction().begin();
        em.getTransaction().commit();

        System.out.println("Misha total money is: " + misha.getTotalMoney(em) + " UAH");
        System.out.println("Alla total money is: " + alla.getTotalMoney(em) + " UAH");

        em.close();
        emf.close();
    }
}
