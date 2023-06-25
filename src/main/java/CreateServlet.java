import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        long id = Integer.parseInt(req.getParameter("id"));
        String model = req.getParameter("model");
        String typeOfBody = req.getParameter("type_of_body");
        String engine = req.getParameter("engine");
        long power = Integer.parseInt(req.getParameter("power"));
        String color = req.getParameter("color");
        long cost = Integer.parseInt(req.getParameter("cost"));
        req.setAttribute("id", id);
        req.setAttribute("model", model);
        req.setAttribute("type_of_body", typeOfBody);
        req.setAttribute("engine", engine);
        req.setAttribute("power", power);
        req.setAttribute("color", color);
        req.setAttribute("cost", cost);

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BMW AVTOIDEA", "postgres", "root");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO cars (id, model, type_of_body, engine, power, color, cost)\n" +
                    "VALUES (?,?,?,?,?,?,?);");
            statement.setInt(1, (int) id);
            statement.setString(2, model);
            statement.setString(3, typeOfBody);
            statement.setString(4, engine);
            statement.setLong(5, power);
            statement.setString(6, color);
            statement.setLong(7, cost);
            statement.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
}

