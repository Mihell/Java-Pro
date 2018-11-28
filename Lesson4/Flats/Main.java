import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        String choice = "";

        while (!choice.equals("0")) {
            System.out.println("Choose parameter for selection:");
            System.out.println("1 - by region\n" + "2 - by address \n" + "3 - by area\n" + "4 - by rooms quantity\n"
                    + "5 - by price\n" + "6 - show all flats\n" + "0 - exit\n" + "Make your choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Insert needed region: ");
                    String value = sc.nextLine();
                    showSelectedFlats("region", value);
                    break;

                case "2":
                    System.out.println("Insert needed address: ");
                    value = sc.nextLine();
                    showSelectedFlats("adress", value);
                    break;

                case "3":
                    System.out.println("Insert lower bound of range of area: ");
                    int min = Integer.parseInt(sc.nextLine());
                    System.out.println("Insert upper bound of range of area: ");
                    int max = Integer.parseInt(sc.nextLine());
                    showFlatsInRange("area", min, max);
                    break;

                case "4":
                    System.out.println("Insert lower bound of rooms quantity: ");
                    min = Integer.parseInt(sc.nextLine());
                    System.out.println("Insert upper bound of rooms quantity: ");
                    max = Integer.parseInt(sc.nextLine());
                    showFlatsInRange("rooms", min, max);
                    break;

                case "5":
                    System.out.println("Insert lower bound of price: ");
                    min = Integer.parseInt(sc.nextLine());
                    System.out.println("Insert upper bound of price: ");
                    max = Integer.parseInt(sc.nextLine());
                    showFlatsInRange("price", min, max);
                    break;

                case "6":
                    showAllFlats();
                    break;

                case "0": break;

                default:
                    System.out.println("Wrong choice!");
                    break;
            }
        }
    }

    public static void showSelectedFlats(String param, String value) throws SQLException {
        DBProperties properties = new DBProperties();
        try (Connection conn = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
             Statement statement = conn.createStatement()) {

            ResultSet rs = statement.executeQuery("SELECT * FROM flats WHERE " + param + " ='" + value + "';");
            printResult(rs);
        }
    }

    public static void showFlatsInRange(String param, int min, int max) throws SQLException{
        DBProperties properties = new DBProperties();
        try(Connection connection = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
        Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT * FROM flats WHERE " + param + " >= " + min
                    + " AND " + param + " <= " + max + ";");
            printResult(rs);
        }
    }

    public static void showAllFlats() throws SQLException {
        DBProperties properties = new DBProperties();
        try (Connection conn = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword());
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM flats;");
            printResult(rs);

        }
    }

    private static void printResult(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        System.out.println("------------------------------------------------------------------------------");

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.printf("%-18s", metaData.getColumnName(i));
        }

        System.out.println();
        System.out.println("------------------------------------------------------------------------------");

        while (rs.next()) {

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.printf("%-18s", rs.getString(i));
            }

            System.out.println();
        }
        System.out.println();
    }


}
