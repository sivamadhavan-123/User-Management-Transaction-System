package org.example.Controller;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.DAO.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/deposite")
public class Deposite extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();
        if(session!=null && session.getAttribute("role").equals("USER")){
            int amount = Integer.parseInt(req.getParameter("balance"));

            String username=session.getAttribute("username").toString();
            UserDao userDao = new UserDao();
            try {
                boolean rs=userDao.deposite(amount,username);
                if(rs){
                    out.println("amount:"+amount+" deposited");
                }else {
                    out.println("amount:"+amount+" not deposited");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            out.println("You are not logged in");
        }

    }
}
