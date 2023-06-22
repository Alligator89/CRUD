import java.sql.*;

public class Read {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");


            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BMW AVTOIDEA", "postgres", "root");


            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars WHERE id=?");
            statement.setInt(1, 7);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long idResult = resultSet.getLong("id");
                String model = resultSet.getString("model");
                String typeOfBody = resultSet.getString("type_of_body");
                String engine = resultSet.getString("engine");
                long power = resultSet.getLong("power");
                String color = resultSet.getString("color");
                long cost = resultSet.getLong("cost");
                System.out.println("Id: " + idResult + " model: " + model + " typeOfBody: " + typeOfBody + " engine: " + engine + " power: " + power + " color: " + color + " cost: " + cost);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
