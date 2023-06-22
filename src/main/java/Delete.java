import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");


            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BMW AVTOIDEA", "postgres", "root"); //URL (хост, порт, имя_базы данных), логин, пароль


            PreparedStatement statement = connection.prepareStatement("DELETE FROM cars WHERE id=?");
            statement.setInt(1, 7);
            int result = statement.executeUpdate();
            System.out.println(result);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
