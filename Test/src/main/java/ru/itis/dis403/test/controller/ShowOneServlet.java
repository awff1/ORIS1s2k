package ru.itis.dis403.test.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.dis403.test.model.Product;
import ru.itis.dis403.test.repository.ProductRepository;

import java.io.IOException;

@WebServlet("/showone")
public class ShowOneServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(ShowOneServlet.class);

    ProductRepository repository = new ProductRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("product", repository.getById(Long.parseLong(request.getParameter("id"))));
        } catch (Exception e) {
            request.setAttribute("errormessage", e.getMessage());
        }

        request.getRequestDispatcher("/showone.ftlh").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product p = new Product();
        p.setId(Long.parseLong(request.getParameter("id")));
        p.setName(request.getParameter("name"));
        p.setDescription(request.getParameter("description"));
        p.setPrice(Double.parseDouble(request.getParameter("price")));
        p.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        p.setManufacturer(request.getParameter("manufacturer"));

        try {
            repository.update(p);
        } catch (Exception e) {
            request.setAttribute("errormessage", e.getMessage());
            request.setAttribute("product", p);
            request.getRequestDispatcher("/showone.ftlh").forward(request, response);
            return;
        }

        response.sendRedirect("./show");
    }
}
