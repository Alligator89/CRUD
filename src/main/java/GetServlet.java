import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/get")
public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h1>Enter id:</h1>");
       int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BMW AVTOIDEA", "postgres", "root");

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Car> carList = new ArrayList<>();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setModel(resultSet.getString("model"));
                car.setTypeOfBody(resultSet.getString("type_of_body"));
                car.setEngine(resultSet.getString("engine"));
                car.setPower(resultSet.getLong("power"));
                car.setColor(resultSet.getString("color"));
                car.setCost(resultSet.getLong("cost"));
                carList.add(car);
                System.out.println(carList);
                printWriter.println(carList);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}

