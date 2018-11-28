import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "";

        while(!choice.equals("0")) {
            System.out.println("Choose what you want to do:  1 - create new product, 2 - create new client, 3 - create new order, 0 - exit");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter name of product: ");
                    String name = sc.nextLine();
                    System.out.println("Enter price of product: ");
                    int price = Integer.parseInt(sc.nextLine());
                    addProduct(name, price);
                    break;

                case "2":
                    System.out.println("Enter name of client: ");
                    name = sc.nextLine();
                    System.out.println("Enter age of client: ");
                    int age = Integer.parseInt(sc.nextLine());
                    addClient(name, age);
                    break;
                case "3":
                    System.out.println("Enter client ID for new order:");
                    int clientId = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter product ID for new order:");
                    int prodId = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter quantity of products: ");
                    int quantity = Integer.parseInt(sc.nextLine());
                    addOrder(clientId, prodId, quantity);

                case "0": break;

                default: System.out.println("Wrong choice!");
            }
        }
    }

    public static void addProduct(String name, int age ){
        DBProperties properties = new DBProperties();
        try(Connection connection = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword())){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Clients (name, age) VALUES (?, ?);");
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addClient(String name, int age){
        DBProperties properties = new DBProperties();
        try(Connection connection = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword())){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Clients (name, age) VALUES (?, ?);");
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addOrder(int clientId, int prodId, int quantity){
        DBProperties properties = new DBProperties();
        try {
            Connection connection = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Orders (clientId, prodId, quantity) VALUES (?,?,?)");
            statement.setInt(1, clientId);
            statement.setInt(2, prodId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
