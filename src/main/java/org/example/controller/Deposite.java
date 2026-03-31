package org.example.controller;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/deposite")
public class Deposite extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        try(PrintWriter out = resp.getWriter()) {
            if (session != null && "USER".equals(session.getAttribute("role"))) {

                String balanceparam =req.getParameter("balance");
                if(balanceparam == null){
                    out.println("Balance parameter is missing");
                    return;
                }
                int amount;
                try {
                    amount = Integer.parseInt(balanceparam);
                } catch (NumberFormatException e) {
                    out.println("Invalid amount format");
                    return;
                }

                String username = session.getAttribute("username").toString();
                UserDao userDao = new UserDao();
                try {
                    boolean rs = userDao.deposite(amount, username);
                    if (rs) {
                        out.println("amount:" + amount + " deposited");
                    } else {
                        out.println("amount:" + amount + " not deposited");
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                out.println("You are not logged in");
            }
        }

    }
}
