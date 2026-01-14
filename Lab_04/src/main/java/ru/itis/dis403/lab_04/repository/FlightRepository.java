package ru.itis.dis403.lab_04.repository;

import ru.itis.dis403.lab_04.model.Flight;
import ru.itis.dis403.lab_04.service.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {

    public List<Flight> getFlights(String airportCode, String flightType) {

        List<Flight> flights = new ArrayList<>();

        String sqlDepartures = "select f.flight_id, f.scheduled_departure, f.scheduled_arrival, f.status, a.airport_name -> 'ru' AS airport_name " +
                "from bookings.flights f " +
                "join bookings.routes r on f.route_no = r.route_no " +
                "join bookings.airports_data a on r.arrival_airport = a.airport_code " +
                "where r.departure_airport = ? " +
                "and f.scheduled_departure::date = current_date " +
                "order by f.scheduled_departure";

        String sqlArrivals = "select f.flight_id, f.scheduled_departure, f.scheduled_arrival, f.status, a.airport_name -> 'ru' AS airport_name " +
                "from bookings.flights f " +
                "join bookings.routes r on f.route_no = r.route_no " +
                "join bookings.airports_data a on r.departure_airport = a.airport_code " +
                "where r.arrival_airport = ? " +
                "and f.scheduled_departure::date = current_date " +
                "order by f.scheduled_departure";

        String sql;

        if ("departures".equalsIgnoreCase(flightType)) {
            sql = sqlDepartures;
        } else if ("arrivals".equalsIgnoreCase(flightType)) {
            sql = sqlArrivals;
        } else {
            sql = sqlDepartures;
        }

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, airportCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Flight flight = new Flight();
                flight.setFlightId(resultSet.getString("flight_id"));
                flight.setDepartureTime(resultSet.getString("scheduled_departure"));
                flight.setArrivalTime(resultSet.getString("scheduled_arrival"));
                flight.setStatus(resultSet.getString("status"));
                flight.setAirportName(resultSet.getString("airport_name"));

                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }
}
