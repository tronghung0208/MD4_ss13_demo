package com.example.ss13_demo.model.dao;

import com.example.ss13_demo.model.entity.Category;
import com.example.ss13_demo.model.entity.Product;
import com.example.ss13_demo.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOIplm implements ProductDAO {
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        Connection connection = null;
        connection = ConnectionDB.openConnection();

        try {
            CallableStatement prepareStatement = connection.prepareCall("CALL PROC_GET_ALL_PRODUCTS");
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setPrice(rs.getFloat("price"));
                product.setImage(rs.getString("image"));
                Category category = categoryDAO.findById(rs.getInt("categoryId"));
                product.setCategory(category);
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;

    }

    @Override
    public Boolean create(Product product) {
        Boolean isCheck = false;
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL PROC_CREATE_PRODUCT(?,?,?,?)");
            callableStatement.setString(1, product.getProductName());
            callableStatement.setDouble(2, product.getPrice());
            callableStatement.setString(3, product.getImage());
            callableStatement.setInt(4, product.getCategory().getCategoryId());
            int check = callableStatement.executeUpdate();
            if (check > 0) {
                isCheck = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return isCheck;

    }

    @Override
    public Boolean update(Product product, Integer id) {
        return null;
    }

    @Override
    public Product findById(Integer id) {
        Product product = new Product();
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL PRODUCT_BY_ID(?)");
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()){
               product.setProductId(rs.getInt("productId"));
               product.setProductName(rs.getString("productName"));
               product.setPrice(rs.getFloat("price"));
               product.setImage(rs.getString("image"));
               Category category=categoryDAO.findById(rs.getInt("categoryId"));
               product.setCategory(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void delete(Integer id) {

    }
}
