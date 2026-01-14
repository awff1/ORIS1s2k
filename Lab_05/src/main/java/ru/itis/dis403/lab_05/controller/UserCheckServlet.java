package ru.itis.dis403.lab_05.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab_05.model.User;
import ru.itis.dis403.lab_05.repository.UserRepository;

import java.io.IOException;

@WebServlet("/usercheck")
public class UserCheckServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(UserCheckServlet.class);
    private UserRepository userRepository = new UserRepository();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String resource = "/index.ftlh";

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (session == null || session.getAttribute("user") == null) {

            User user = userRepository.findByUsername(username);

            if ((username.equals("admin") && password.equals("admin"))
                    || (user != null && user.getPassword().equals(password))) {
                session = request.getSession(true);
                session.setAttribute("user", username);
                resource = "/index.ftlh";
            } else {
                request.setAttribute("errormessage", "Неверное имя пользователя или пароль!");
                resource = "/login.ftlh";
            }
        } else if (session != null && !session.getAttribute("user").equals(username)) {
            session.invalidate();
            session = request.getSession(true);
            session.setAttribute("user", username);
            resource = "/index.ftlh";
        }

        request.getRequestDispatcher(resource)
                .forward(request, response);
    }

}