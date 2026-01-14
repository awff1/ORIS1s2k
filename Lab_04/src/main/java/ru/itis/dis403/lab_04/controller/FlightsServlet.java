package ru.itis.dis403.lab_04.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.dis403.lab_04.service.FlightService;

import java.io.IOException;

@WebServlet("/flights")
public class FlightsServlet extends HttpServlet {

    private FlightService flightService = new FlightService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        flightService.fillAttributes(request);
        request.getRequestDispatcher("/flights.ftlh")
                .forward(request, response);
    }
}