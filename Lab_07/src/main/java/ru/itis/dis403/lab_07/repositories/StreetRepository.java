package ru.itis.dis403.lab_07.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab_07.models.City;
import ru.itis.dis403.lab_07.models.Street;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StreetRepository {

    final static Logger logger = LogManager.getLogger(StreetRepository.class);

    public List<Street> getAll() throws Exception {
        List<Street> result = new ArrayList<>();

        Connection connection = DBConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM street");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            result.add(new Street(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("city_id") ));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }

    public List<Street> findByCityId(Long cityId) throws Exception {
        List<Street> result = new ArrayList<>();

        Connection connection = DBConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM street WHERE city_id = ?");
        statement.setLong(1, cityId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            result.add(new Street(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("city_id") ));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }
}
