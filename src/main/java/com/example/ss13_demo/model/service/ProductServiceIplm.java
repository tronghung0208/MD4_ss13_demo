package com.example.ss13_demo.model.service;

import com.example.ss13_demo.model.dao.CategoryDAO;
import com.example.ss13_demo.model.dao.ProductDAO;
import com.example.ss13_demo.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceIplm implements ProductService{
  @Autowired
  ProductDAO productDAO;
    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public Boolean create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Boolean update(Product product, Integer id) {
        return null;
    }

    @Override
    public Product findById(Integer id) {
        return productDAO.findById(id);
    }

    @Override
    public void delete(Integer id) {

    }
}
