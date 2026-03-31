package org.example.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/logout")
public class Logout extends HttpServlet {
    private static final Logger logger= LogManager.getLogger(Logout.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
logger.trace("Entering logout doPost");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);


        if(session != null) {


            session.invalidate();

            response.setStatus(HttpServletResponse.SC_OK);
            out.println("You have been logged out successfully");
            logger.info("You have been logged out successfully");

        }else {
          
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not logged in ");
            logger.warn("You are not logged in");

        }

logger.trace("Exiting logout doPost");

    }


}
