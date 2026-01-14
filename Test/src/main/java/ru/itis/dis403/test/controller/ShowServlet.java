package ru.itis.dis403.test.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.dis403.test.repository.ProductRepository;

import java.io.IOException;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(ShowServlet.class);

    ProductRepository repository = new ProductRepository();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

        try {
            request.setAttribute("products", repository.getAll());
        } catch (Exception e) {
            request.setAttribute("errormessage", e.getMessage());
        }

        request.getRequestDispatcher("/show.ftlh").forward(request, response);
    }
}
