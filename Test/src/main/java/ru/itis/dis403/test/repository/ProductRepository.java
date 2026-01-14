package ru.itis.dis403.test.repository;

import ru.itis.dis403.test.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public List<Product> getAll() throws Exception {
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from products order by id");
        ResultSet resultSet = statement.executeQuery();

        List<Product> list = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getDouble("price"));
            product.setQuantity(resultSet.getInt("quantity"));
            product.setManufacturer(resultSet.getString("manufacturer"));
            list.add(product);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return list;
    }

    public Product getById(long id) throws Exception {
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from products where id=?");
        statement.setLong(1, id);
        ResultSet resultSets = statement.executeQuery();

        Product product = null;
        if (resultSets.next()) {
            product = new Product();
            product.setId(resultSets.getLong("id"));
            product.setName(resultSets.getString("name"));
            product.setDescription(resultSets.getString("description"));
            product.setPrice(resultSets.getDouble("price"));
            product.setQuantity(resultSets.getInt("quantity"));
            product.setManufacturer(resultSets.getString("manufacturer"));
        }

        resultSets.close();
        statement.close();
        connection.close();

        return product;
    }

    public void add(Product p) throws Exception {
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("insert into products(name,description,price,quantity,manufacturer) values(?,?,?,?,?)");

        statement.setString(1, p.getName());
        statement.setString(2, p.getDescription());
        statement.setDouble(3, p.getPrice());
        statement.setInt(4, p.getQuantity());
        statement.setString(5, p.getManufacturer());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public void update(Product p) throws Exception {
        Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("update products set name=?,description=?,price=?,quantity=?,manufacturer=? where id=?");

        statement.setString(1, p.getName());
        statement.setString(2, p.getDescription());
        statement.setDouble(3, p.getPrice());
        statement.setInt(4, p.getQuantity());
        statement.setString(5, p.getManufacturer());
        statement.setLong(6, p.getId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
