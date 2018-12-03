package pack;

import javax.persistence.*;

@Entity
@Table(name = "Menu")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    private int price;

    @Column(nullable = false)
    private double weight;

    private int discount;

    public Dish() {
    }

    public Dish(String name, int price, double weight, int discount) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }
}
