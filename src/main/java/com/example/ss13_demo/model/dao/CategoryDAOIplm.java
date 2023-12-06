package com.example.ss13_demo.model.dao;

import com.example.ss13_demo.model.entity.Category;
import com.example.ss13_demo.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOIplm implements CategoryDAO {
    @Override
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL PROC_GET_ALL_CATEGORY()");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setStatus(rs.getBoolean("status"));
                list.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Boolean create(Category category) {
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL PROC_CREATE_CATEGORY(?,?)");
            callableStatement.setString(1, category.getCategoryName());
            callableStatement.setBoolean(2, category.getStatus());
            int rs = callableStatement.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return false;
    }

    @Override
    public Boolean update(Category category, Integer id) {
        Boolean ischeck=false;
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement=connection.prepareCall("CALL UPDATE_CATEGORY(?,?,?)");
            callableStatement.setInt(1,category.getCategoryId());
            callableStatement.setString(2,category.getCategoryName());
            callableStatement.setBoolean(3,category.getStatus());
            int check =callableStatement.executeUpdate();
            if (check>0){
                ischeck=true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }


        return ischeck;
    }

    @Override
    public Category findById(Integer id) {
        Category category = new Category();
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL CATEGORY_BY_ID(?)");
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return category;
    }

    @Override
    public void delete(Integer integer) {

    }
}
