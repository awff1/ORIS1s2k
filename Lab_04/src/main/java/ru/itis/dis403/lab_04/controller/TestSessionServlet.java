package ru.itis.dis403.lab_04.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/testsession")
public class TestSessionServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(TestSessionServlet.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean flag = false;

        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("JSESSIONID")){
                    request.setAttribute("sessionID", cookie.getValue());
                    flag = true;
                    break;
                }
            }
        }

        if(!flag){
            request.setAttribute("sessionID","JSESSIONID не нашли");
            Cookie cookie = new Cookie("JSESSIONID", "123");
            response.addCookie(cookie);
        }

        request.getRequestDispatcher("/session.ftlh").forward(request,response);
    }
}
