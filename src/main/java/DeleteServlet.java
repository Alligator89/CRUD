import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h2>Deleting ID:</h2>");
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);

        Connection connection;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BMW AVTOIDEA", "postgres", "root");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM cars WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            boolean isExist = false;
            if (!isExist) {
                req.getRequestDispatcher("/succeed.jsp").forward(req, resp);
            } else {
                printWriter.println("<h3> Id is not exist </h3>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

