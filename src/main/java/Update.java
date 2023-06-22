import java.sql.*;

public class Update {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");


            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BMW AVTOIDEA", "postgres", "root"); //URL (хост, порт, имя_базы данных), логин, пароль


            PreparedStatement statement = connection.prepareStatement("UPDATE cars SET engine=?, color=?");
            statement.setString(1, "Gasoline");
            statement.setString(2, "White");
            int result = statement.executeUpdate();
            System.out.println(result);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
