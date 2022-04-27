package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{
    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();

        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery("SELECT * FROM products");
        while (resultSet.next()){
            products.add(new Product(resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getLong(3)));
        }
        dataSource.getConnection().createStatement().close();
        dataSource.getConnection().close();
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE identifier= ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next())
            throw new RuntimeException("Wrong id");
        Product product = new Product(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getLong(3));
        preparedStatement.close();
        connection.close();
        return Optional.of(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE products SET name = ?, price = ? WHERE identifier = ?;"
        );
        preparedStatement.setString(1, product.getName());
        preparedStatement.setLong(2, product.getPrice());
        preparedStatement.setLong(3, product.getId());

        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void save(Product product) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products VALUES (?, ?, ?);");
        preparedStatement.setLong(1, product.getId());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setLong(3, product.getPrice());
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM products WHERE identifier = ?;"
        );
        preparedStatement.setLong(1, id);
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }
}
