package ru.itis.dis403.lab_07.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab_07.models.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {

    final static Logger logger =  LogManager.getLogger(CityRepository.class);

    public List<City> getAll() throws Exception {
        List<City> result = new ArrayList<>();

        Connection connection = DBConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM city");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            result.add(new City(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("comment")));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }

    public City findById(long id) throws Exception {
        City result = null;

        Connection connection = DBConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            result = new City(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("comment"));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }
}
