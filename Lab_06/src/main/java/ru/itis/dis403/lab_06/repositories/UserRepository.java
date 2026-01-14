package ru.itis.dis403.lab_06.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.dis403.lab_06.controllers.RegistrationServlet;
import ru.itis.dis403.lab_06.models.User;

import java.sql.*;

public class UserRepository {

    final static Logger logger = LogManager.getLogger(UserRepository.class.getName());

    public void addUser(User user) throws Exception {
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement statement = connection.prepareStatement("select id from nextval('user_seq') as id");

        ResultSet resultSet = statement.executeQuery();

        Long id = null;
        if (resultSet.next()) {
            id = resultSet.getLong("id");
        }
        resultSet.close();
        statement.close();

        if (id != null) {
            user.setId(id);
        } else {
            throw new Exception("Не удалось присвоить идентификатор!");
        }

        statement = connection.prepareStatement("insert into users (id, username, hashpassword) values (?, ?, ?)");
        statement.setLong(1, id);
        statement.setString(2, user.getUserName());
        statement.setString(3, user.getHashPassword());
        int countRow = statement.executeUpdate();
        statement.close();

        statement = connection.prepareStatement("insert into userinfo (id, lastname, firstname, phone) values ( ?, ? , ? , ?)");
        statement.setLong(1, id);
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getPhone());
        countRow = statement.executeUpdate();
        statement.close();

        connection.commit();
        connection.close();
    }

    public String getUserPasswordHash(String username) throws Exception {
        String result = null;
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("select hashpassword from users where username = ?");
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            result = resultSet.getString("hashpassword");
        }
        resultSet.close();
        statement.close();
        connection.close();

        return result;
    }

    public User getUserByUserName(String userName) throws Exception {
        User user = null;
        Connection connection = DBConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("select id, username, hashpassword, userrole, lastname, firstname, phone"
        + " from users natural join userinfo where username = ?");
        statement.setString(1, userName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUserName(resultSet.getString("username"));
            user.setHashPassword(resultSet.getString("hashpassword"));
            user.setUserRole(resultSet.getString("userrole"));
            user.setLastName(resultSet.getString("lastname"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setPhone(resultSet.getString("phone"));
        }
        resultSet.close();
        statement.close();
        connection.close();

        return user;
    }
}
