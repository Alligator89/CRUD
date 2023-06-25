import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/change-login")
public class ChangeLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        req.getRequestDispatcher("/change.jsp").forward(req, resp);
        int id = Integer.parseInt(req.getParameter("id"));
        String model = req.getParameter("model");
        req.setAttribute("id", id);
        req.setAttribute("model", model);

        Connection connection;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BMW AVTOIDEA", "postgres", "root");
            PreparedStatement statement = connection.prepareStatement("UPDATE cars SET model=? WHERE id=?");
            statement.setString(1, model);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
           printWriter.println(resultSet);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
}
