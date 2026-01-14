package ru.itis.dis403.lab_04.repository;

import ru.itis.dis403.lab_04.model.Airport;
import ru.itis.dis403.lab_04.model.Flight;
import ru.itis.dis403.lab_04.service.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportRepository {

    public List<Airport> findAll() {

        List<Airport> airports = new ArrayList<>();

        String sql = "select airport_code, airport_name -> 'ru' as airport_name from bookings.airports_data order by airport_name";

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Airport airport = new Airport();
                airport.setAirportCode(resultSet.getString("airport_code"));
                airport.setAirportName(resultSet.getString("airport_name"));

                airports.add(airport);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airports;
    }
}
