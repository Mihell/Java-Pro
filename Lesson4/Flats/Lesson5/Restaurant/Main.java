package pack;

import javax.persistence.*;
import javax.transaction.Transaction;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static EntityManagerFactory emf;
    private static EntityManager manager;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("Menu");
        manager = emf.createEntityManager();

        boolean exit = false;
        while (!exit) {
            System.out.println("1 - add dish, 2 - select dish by price, 3 - show dishes only with discount, " +
                    "4 - select dishes with total weight before 1 kg.");
            System.out.println("0 - exit");
            String choice = sc.nextLine();

            switch (choice) {
                case "0":
                    exit = true;
                    break;
                case "1":
                    addDish();
                    break;
                case "2":
                    selectDishByPrice();
                    break;
                case "3":
                    withDiscount();
                    break;
                case "4":
                    weightBeforeKG();
                    break;
                default:
                    System.out.println("Wrong choice!");
            }

        }
        sc.close();
        manager.close();
        emf.close();
    }

    public static void addDish() {
        System.out.println("Enter dish name: ");
        String name = sc.nextLine();
        System.out.println("Enter price of dish: ");
        int price = Integer.parseInt(sc.nextLine());
        System.out.println("Enter weight of dish: ");
        double weight = Double.parseDouble(sc.nextLine());
        System.out.println("Enter 1 - if discount available for this dish, otherwise  - 0: ");
        int discount = Integer.parseInt(sc.nextLine());
        Dish dish = new Dish(name, price, weight, discount);

        manager.getTransaction().begin();
        try {
            manager.persist(dish);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    public static void selectDishByPrice() {
        System.out.println("Select min price of dish: ");
        int minPrice = Integer.parseInt(sc.nextLine());
        System.out.println("Select max price of dish: ");
        int maxPrice = Integer.parseInt(sc.nextLine());
        Query query = manager.createQuery("SELECT d FROM Dish d WHERE d.price BETWEEN :min AND :max", Dish.class);
        query.setParameter("min", minPrice);
        query.setParameter("max", maxPrice);
        List<Dish> list = (List<Dish>) query.getResultList();
        System.out.println();
        for(Dish d : list){
            System.out.println(d);
        }
    }

    public static void withDiscount(){
        Query query = manager.createQuery("SELECT d FROM Dish d WHERE d.discount=1", Dish.class);
        List<Dish> list = query.getResultList();
        System.out.println();
        for(Dish dish : list){
            System.out.println(dish);
        }
    }

    public static void weightBeforeKG(){
        int totalWeight = 0;
        while(true){
            System.out.println("total weight of you dishes is " + totalWeight);
            System.out.println("Enter name of dish to add or 0 to end");
            String choice = sc.nextLine();
            Dish dish;
            if(choice.equals("0")){
                break;
            } else{
                try{
                    Query query = manager.createQuery("SELECT d FROM Dish d WHERE d.name=:name", Dish.class);
                    query.setParameter("name", choice);
                    dish = (Dish)query.getSingleResult();
                } catch (NoResultException e){
                    System.out.println("Dish not found");
                    return;
                } catch (NonUniqueResultException e){
                    System.out.println("No unique dish name");
                    return;
                }
                if(totalWeight + dish.getWeight() > 1000){
                    System.out.println("you can't choose this dish - total weight will be more than 1 kg, choose another.");
                } else {
                    System.out.println("You add " + choice);
                    totalWeight += dish.getWeight();
                }

            }
        }
    }

}
