import java.sql.*;

public class Create {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");


            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BMW AVTOIDEA", "postgres", "root");


            PreparedStatement statement = connection.prepareStatement("INSERT INTO cars (id, model, type_of_body, engine, power, color, cost)\n" +
                    "VALUES (?,?,?,?,?,?,?)");
            statement.setInt(1, 7);
            statement.setString(2, "BMW E60");
            statement.setString(3, "Universal");
            statement.setString(4, "Diesel");
            statement.setLong(5, 205);
            statement.setString(6, "Black Edition");
            statement.setLong(7, 10000);
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
        } catch (SQLException | ClassNotFoundException ignored) {
        }
    }
}
