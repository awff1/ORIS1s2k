package ru.itis.dis403.lab_04.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.dis403.lab_04.model.Airport;
import ru.itis.dis403.lab_04.model.Flight;
import ru.itis.dis403.lab_04.repository.AirportRepository;
import ru.itis.dis403.lab_04.repository.FlightRepository;

import java.util.List;

public class FlightService {

    private FlightRepository flightRepository = new FlightRepository();
    private AirportRepository airportRepository = new AirportRepository();


    public void fillAttributes(HttpServletRequest request) {

        List<Airport> airports = airportRepository.findAll();
        request.setAttribute("airports", airports);

        String airportCode = request.getParameter("airport");
        if (airportCode == null && !airports.isEmpty()) {
            airportCode = airports.get(0).getAirportCode();
        }
        String flightType = request.getParameter("type");
        if (flightType == null) {
            flightType = "departures";
        }

        request.setAttribute("selectedAirport", airportCode);
        request.setAttribute("selectedType", flightType);

        List<Flight> flights = flightRepository.getFlights(airportCode, flightType);
        request.setAttribute("flights", flights);
    }
}
